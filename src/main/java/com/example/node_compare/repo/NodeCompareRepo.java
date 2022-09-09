package com.example.node_compare.repo;

import com.example.node_compare.entity.NodeCompareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Spark
 * @CreateTime: 2022-09-09
 * @Description: TODO
 */
@Repository
public interface NodeCompareRepo extends JpaRepository<NodeCompareEntity, String> {

}
