package com.hackeps2019.server.service;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.ReportStatus;
import com.hackeps2019.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> findAllByUserAndStatus(String username, ReportStatus status) {
        return this.deviceRepository.findAllByUserAndStatus(username, status);
    }
}
