package com.hackeps2019.server.domain;

import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Devices")
public class Device {
    @Id
    private String id;
    private String name;
    private ReportStatus status;
    @Indexed(direction = IndexDirection.ASCENDING)
    private List<Report> reports;
}
