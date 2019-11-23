package com.hackeps2019.server.controller;

import com.hackeps2019.server.domain.Report;
import com.hackeps2019.server.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/{name}")
    public void receiveReport(@PathVariable String name,
                              @RequestBody Report report){
        reportService.receiveReport(name, report);
    }

}
