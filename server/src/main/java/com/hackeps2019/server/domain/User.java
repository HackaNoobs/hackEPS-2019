package com.hackeps2019.server.domain;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private String name;
    private String password;
    private List<String> devices;
}
