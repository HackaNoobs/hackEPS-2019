package com.hackeps2019.server.domain;

import java.util.List;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    private String name;
    private String password;
    private List<String> devices;
}
