package com.example.node_compare.controller;

import com.example.node_compare.entity.NodeCompareEntity;
import com.example.node_compare.repo.NodeCompareRepo;
import com.example.node_compare.service.NodeCompareService;
import com.example.node_compare.util.TimerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Spark
 * @CreateTime: 2022-09-09
 * @Description: TODO
 */
@EnableAsync
@RestController
public class UploadLogController {

    private static final Logger log = LoggerFactory.getLogger(UploadLogController.class);

    @Autowired
    private NodeCompareService service;

    @PostMapping("/uploadLog")
    public long uploadLog(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req, @RequestParam("type") int type){
        log.info("-------------uploadLog--------------------");
        long start = new Date().getTime();
        long size = 0;
        List<String> memoryList = new ArrayList<>();
        try {
            String data = new String(uploadFile.getBytes());
            String[] rows = data.split("\n");
            size = rows.length;
            log.info("type: {}, size: {}", type, size);
            long repeatCount = 0;
//            for(String row : rows) {
//                String hash = row.split("=")[1];
//                if (memoryList.contains(hash)){
//                    repeatCount ++;
//                    continue;
//                }
//                memoryList.add(hash);
//                String timeStr = row.substring(6, 24);
//                service.save(hash, type, timeStr);
//            }
            List<NodeCompareEntity> list = new ArrayList<>();
            for(String row : rows) {
                String hash = row.split("=")[1];
                if (memoryList.contains(hash)){
                    repeatCount ++;
                    continue;
                }
                memoryList.add(hash);
                String timeStr = row.substring(6, 24);
                NodeCompareEntity entity = new NodeCompareEntity();
                entity.setHashKey(hash);
                entity.setLogType(type);
                entity.setLogTime(TimerUtil.parseDate(timeStr));
                list.add(entity);
                if (list.size() == 1000) {
                    service.saveAll(list);
                    list = new ArrayList<>();
                }

            }
            service.saveAll(list);
            log.info("upload log finish, repeatCount: {}, cost: {}", repeatCount, new Date().getTime() - start);
        }catch (Exception e) {
            log.error("{}", e);
        }
        return size;
    }



}
