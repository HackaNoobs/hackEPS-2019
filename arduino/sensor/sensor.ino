#include <ArduinoLowPower.h>
#include <SigFox.h>
#include <DHT.h>

#define DHTPIN 1
#define DHTTYPE DHT11
#define UINT16_t_MAX 65536
#define INT16_t_MAX UINT16_t_MAX/2

DHT dht(DHTPIN, DHTTYPE);

float temperature;
float t;
float h;

/*
    ATTENTION - the structure we are going to send MUST
    be declared "packed" otherwise we'll get padding mismatch
    on the sent data - see http://www.catb.org/esr/structure-packing/#_structure_alignment_and_padding
    for more details
*/
typedef struct __attribute__ ((packed)) sigfox_message {
  uint8_t status;
  int16_t moduleTemperature;
  int16_t t;
  int16_t h;
} SigfoxMessage;

// stub for message which will be sent
SigfoxMessage msg;

void setup() {
  if (!SigFox.begin()) {
    // Something is really wrong, try rebooting
    // Reboot is useful if we are powering the board using an unreliable power source
    // (eg. solar panels or other energy harvesting methods)
    reboot();
  }
  
  //Send module to standby until we need to send a message
  SigFox.end();
  
  SigFox.debug();
  
  dht.begin();
}


void loop() {
  
  t = dht.readTemperature();
  msg.t = convertoFloatToInt16(t, 60, -60);
  
  h = dht.readHumidity();
  msg.h = convertoFloatToUInt16(h, 110);
  
  // Start the module
  SigFox.begin();
  // Wait at least 30ms after first configuration (100ms before)
  delay(100);

  // We can only read the module temperature before SigFox.end()
  temperature = SigFox.internalTemperature();
  msg.moduleTemperature = convertoFloatToInt16(temperature, 60, -60);
  Serial.println("Temperature= " + (String) msg.t);
  Serial.println("Humidty= " + (String) msg.h);
  
  // Clears all pending interrupts
  SigFox.status();
  delay(1);

  SigFox.beginPacket();
  SigFox.write((uint8_t*)&msg, 12);

  int lastMessageStatus = SigFox.endPacket();

  SigFox.end();

  //Sleep for 15 minutes
  LowPower.sleep(15 * 60 * 1000);
}

void reboot() {
   NVIC_SystemReset();
   while (1) ;
}

int16_t convertoFloatToInt16(float value, long max, long min) {
        float conversionFactor = (float) (INT16_t_MAX) / (float)(max - min);
        return (int16_t)(value * conversionFactor);
}

uint16_t convertoFloatToUInt16(float value, long max) {
        float conversionFactor = (float) (UINT16_t_MAX) / (float)(max);
        return (uint16_t)(value * conversionFactor);
}
