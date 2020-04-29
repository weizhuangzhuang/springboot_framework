package com.wzz.demo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "users")
public class User {

    @Id
    private Integer id;
    @Column(name = "userId")
    private String userId;
    private String username;
    private String password;
    private String address;
}
