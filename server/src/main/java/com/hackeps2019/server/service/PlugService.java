package com.hackeps2019.server.service;

import com.hackeps2019.server.domain.SigFoxData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlugService {

    public void processEntrantData(List<SigFoxData> dataList){
        int mediumTemp = getMediumTemperature(dataList);
        modifyPlug(mediumTemp);
    }

    private void modifyPlug(int mediumTemp) {
        // TODO connection with ZOOZEE api
        if (mediumTemp > 25) {
            // TODO open/close order
        } else {
            // TODO open/close order
        }
    }

    private int getMediumTemperature(List<SigFoxData> dataList) {
        int mediumTemp = dataList.stream().mapToInt(SigFoxData::getTemperature).sum();
        mediumTemp = mediumTemp / dataList.size();
        return mediumTemp;
    }

}
