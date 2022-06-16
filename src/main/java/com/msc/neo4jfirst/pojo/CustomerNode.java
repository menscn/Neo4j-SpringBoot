package com.msc.neo4jfirst.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.pojo
 * @Description: 消费者节点，及其属性
 */
@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
}

