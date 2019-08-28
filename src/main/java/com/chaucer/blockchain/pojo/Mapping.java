package com.chaucer.blockchain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name = "mapping")
public class Mapping {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "pseId")
    private String pseId;
    @Column(name = "realId")
    private String realId;

}
