package com.hackeps2019.server.utils;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.Report;
import com.hackeps2019.server.domain.ReportStatus;
import com.hackeps2019.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void run(String... strings) throws Exception {

        List<Report> reports = new ArrayList<>();

        //Create a report as example
        Report report1 = new Report();
        report1.setId("report 1");
        report1.setHum("50");
        report1.setTemp("25");

        // Add report to report list
        reports.add(report1);

        // Create device
        Device test = new Device();
        test.setId("test");
        test.setName("Test");
        test.setUser("user");
        test.setStatus(ReportStatus.ON);
        test.setReports(reports);

        // Drop all
        this.deviceRepository.deleteAll();

        //Add things created before
        System.out.println("VAIG A GUARDAR");

        this.deviceRepository.save(test);

        System.out.println("He GUARDAT");
    }
}
