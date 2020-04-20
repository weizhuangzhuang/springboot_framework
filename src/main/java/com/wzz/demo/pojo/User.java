package com.wzz.demo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "users")
public class User {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String address;
}
