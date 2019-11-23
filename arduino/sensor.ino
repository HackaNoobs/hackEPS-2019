/*
  SigFox Simple Weather Station
  by Giovanni Gentile
  0lab.it
  August 2017

  Send to ThingSepeak:
  status
  internal temp
  DHT-11 temp
  DHT-11 humi
*/
#include <ArduinoLowPower.h>
#include <SigFox.h>
#include <DHT.h>

#define UINT16_t_MAX  65536
#define INT16_t_MAX   UINT16_t_MAX/2
#define DHTPIN 1
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

float temperature;
float t;
float h;

typedef struct __attribute__ ((packed)) sigfox_message {
  uint8_t status;
  int16_t moduleTemperature;
  int16_t t;
  int16_t h;
} SigfoxMessage;

// stub for message which will be sent
SigfoxMessage msg;

void setup() {
  dht.begin();

}

void loop() {
  t = dht.readTemperature();
  msg.t = convertoFloatToInt16(t, 60, -60);

  h = dht.readHumidity();
  msg.h = convertoFloatToUInt16(h, 110);

  Serial.println("Temperature:" + msg.t);
  Serial.println("Humidity:" + msg.h);

  //Sleep for 15 minutes
  LowPower.sleep(15 * 60 * 1000);

}

int16_t convertoFloatToInt16(float value, long max, long min) {
  float conversionFactor = (float) (INT16_t_MAX) / (float)(max - min);
  return (int16_t)(value * conversionFactor);
}

uint16_t convertoFloatToUInt16(float value, long max) {
  float conversionFactor = (float) (UINT16_t_MAX) / (float)(max);
  return (uint16_t)(value * conversionFactor);
}

void reboot() {
  NVIC_SystemReset();
  while (1) ;
}
