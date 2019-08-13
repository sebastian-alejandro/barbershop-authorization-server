package com.sebastiansoftwareengineer.barbershopauthorizationserver.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.Map;

@Data
public class Account {

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String role;
    @NonNull
    private Date createdAt;
    @NonNull
    private Date updatedAt;
    private Map<String, Boolean> details;

}
