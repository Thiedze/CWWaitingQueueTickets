package de.cw.wqt.domain;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class User {

    private String name;

    private String fullName;

    private ArrayList<String> roles;

    public User(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
        this.roles = new ArrayList<>();
    }

    public String getFullName() {
        return StringUtils.isNotBlank(this.fullName) ? this.fullName : this.name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }
}
