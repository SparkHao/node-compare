package com.example.node_compare.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Spark
 * @CreateTime: 2022-09-09
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "node_compare")
public class NodeCompareEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 70)
    private String hashKey;

    @Column(nullable = true)
    private Date logTime;

    @Column(nullable = true)
    private int logType;
}
