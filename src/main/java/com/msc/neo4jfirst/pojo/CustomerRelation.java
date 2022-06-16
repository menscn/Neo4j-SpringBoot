package com.msc.neo4jfirst.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.pojo
 * @Description: 节点间关系
 */
@RelationshipEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRelation {
    @Id
    @GeneratedValue
    private Long id;
    private Date createTime;
    private String remark;

    @StartNode
    private CustomerNode customerFrom;

    @EndNode
    private CustomerNode customerTo;

    public CustomerRelation(CustomerNode customerFrom, CustomerNode customerTo, String remark) {
        this.customerFrom = customerFrom;
        this.customerTo = customerTo;
        this.remark = remark;
    }
}

