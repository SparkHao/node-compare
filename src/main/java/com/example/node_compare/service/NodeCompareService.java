package com.example.node_compare.service;

import com.example.node_compare.controller.UploadLogController;
import com.example.node_compare.entity.NodeCompareEntity;
import com.example.node_compare.repo.NodeCompareRepo;
import com.example.node_compare.util.TimerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther: Spark
 * @CreateTime: 2022-09-09
 * @Description: TODO
 */
@Service
public class NodeCompareService {
    @Autowired
    private NodeCompareRepo repo;

    private static final Logger log = LoggerFactory.getLogger(NodeCompareService.class);


    @Async("syncExecutorPool")
    public void save(String hash, int type, String timeStr){
        NodeCompareEntity entity = new NodeCompareEntity();
        entity.setHashKey(hash);
        entity.setLogType(type);
        entity.setLogTime(TimerUtil.parseDate(timeStr));
        NodeCompareEntity save = repo.save(entity);
        log.info("after save: {}", save);
    }
}
