package com.hackeps2019.server.controller;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.ReportStatus;
import com.hackeps2019.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PutMapping("/put")
    public void insert(@RequestBody Device device) {
        this.deviceService.saveDevice(device);
    }

    @PostMapping("/update")
    public void update(@RequestBody Device device) {
        this.deviceService.updateDevice(device);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody String id) {
        this.deviceService.deleteDevice(id);
    }

    @PostMapping("/all")
    @ResponseBody
    public List<Device> getAll(@RequestBody String username) {
        return this.deviceService.findAllByUser(username);
    }

    @PostMapping("/on")
    public List<Device> getAllOn(@RequestBody String username ) {
        return this.deviceService.findAllByUserAndStatus(username, ReportStatus.ON);
    }

    @PostMapping("/off")
    public List<Device> getAllOff(@RequestBody String username ) {
        return this.deviceService.findAllByUserAndStatus(username, ReportStatus.OFF);
    }
}
