package com.hackeps2019.server.controller;

import com.hackeps2019.server.domain.Report;
import com.hackeps2019.server.domain.SigFoxData;
import com.hackeps2019.server.service.PlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SigFoxController {

    @Autowired
    PlugService plugService;

    @PostMapping("/getData")
    public @ResponseBody List<Report> getData (@RequestBody List<SigFoxData> dataList) {
        plugService.processEntrantData(dataList);
        return parseData(dataList);
    }

    private List<Report> parseData(List<SigFoxData> dataList) {
        List<Report> reportList = new ArrayList<>();
        for (SigFoxData a : dataList) {
            Report report = new Report();
            report.setHum(String.valueOf(a.getHumidity()));
            report.setTemp(String.valueOf(a.getTemperature()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            report.setDate(dtf.format(now));
            reportList.add(report);
        }
        return reportList;
    }
}
