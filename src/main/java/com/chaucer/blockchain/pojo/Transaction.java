package com.chaucer.blockchain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Chaucer
 * @date 2019-10-14 16:41
 */

@Getter
@Setter
@ToString
@Table(name = "transaction_data")
public class Transaction {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "label")
    private String label;
    @Column(name = "timeStp")
    private String timeStamp;

}
