package com.chaucer.blockchain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Chaucer
 * @date 2019-08-10 17:44
 */
@Getter
@Setter
@ToString
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private int balance;


}
