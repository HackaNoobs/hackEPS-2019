package com.hackeps2019.server.service;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.Report;
import com.hackeps2019.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    DeviceRepository deviceRepository;

    public void receiveReport(String name, Report report) {
        Device device = deviceRepository.findDeviceByName(name);
        List<Report> reports = device.getReports();
        reports.add(report);
        device.setReports(reports);
        deviceRepository.save(device);
    }
}
