package com.msc.neo4jfirst.dao;

import com.msc.neo4jfirst.pojo.CustomerRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.mapper
 * @Description:
 */
public interface CustomerRelationMapper extends Neo4jRepository<CustomerRelation, Long> {
}
