package com.example.node_compare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NodeCompareApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodeCompareApplication.class, args);
    }

}
