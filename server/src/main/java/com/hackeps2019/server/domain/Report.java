package com.hackeps2019.server.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "Reports")
public class Report {

    @MongoId
    private String id;

    private String date;

    private String temp;

    private String hum;

}
