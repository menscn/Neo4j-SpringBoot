package com.msc.neo4jfirst.dao;

import com.msc.neo4jfirst.pojo.CustomerNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.mapper
 * @Description: 消费者节点数据处理接口
 */
public interface CustomerNodeMapper extends Neo4jRepository<CustomerNode, Long> {
    CustomerNode findByName(String name);

    CustomerNode deleteByName(String name);

    @Query(
            "MATCH c=(cf:CustomerNode)-[r:CustomerRelation]->(ct:CustomerNode) WHERE ct.name=$name OR cf.name=$name RETURN cf")
    List<CustomerNode> findRelationByCustomerNode(String name);

    @Query("MATCH c=(cf:CustomerNode) WHERE cf.id=$id RETURN cf")
    CustomerNode findNodeById(Long id);
}
