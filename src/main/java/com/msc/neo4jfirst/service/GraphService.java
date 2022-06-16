package com.msc.neo4jfirst.service;

import com.msc.neo4jfirst.pojo.CustomerNode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.service
 * @Description: 定义图标接口
 */
@Service
public interface GraphService {
    void deleteNodeById(Long id);

    void deleteNodeByName(String name);

    void delete();

    void addNode(String name, Integer age, String nameTo, String remark);

    void updateNode(Long id, String name, Integer age);

    Iterable<CustomerNode> queryNodes();

    CustomerNode findByName(String name);

    List<CustomerNode> queryNodes(String name);
}
