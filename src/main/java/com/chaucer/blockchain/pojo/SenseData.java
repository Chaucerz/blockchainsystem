package com.chaucer.blockchain.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name = "sense_data")
public class SenseData {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sensedata")
    private String senseData;
    @Column(name = "datatype")
    private String dataType;
    @Column(name = "pseId")
    private String pseId;
    @Column(name = "timeStp")
    private String timeStp;
}















