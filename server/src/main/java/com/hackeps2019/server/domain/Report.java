package com.hackeps2019.server.domain;
import lombok.Data;

@Data
public class Report {
    private String id;
    private String date;
    private String temp;
    private String hum;
}
