package com.hackeps2019.server.controller;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.ReportStatus;
import com.hackeps2019.server.domain.User;
import com.hackeps2019.server.repository.DeviceRepository;
import com.hackeps2019.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;


    @PostMapping("/on")
    public List<Device> getAllOn(@RequestBody String username ) {
        return this.deviceService.findAllByUserAndStatus(username, ReportStatus.ON);
    }

    @PostMapping("/off")
    public List<Device> getAllOff(@RequestBody String username ) {
        return this.deviceService.findAllByUserAndStatus(username, ReportStatus.OFF);
    }
}
