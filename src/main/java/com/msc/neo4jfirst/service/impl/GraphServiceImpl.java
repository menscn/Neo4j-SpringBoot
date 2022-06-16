package com.msc.neo4jfirst.service.impl;

import com.msc.neo4jfirst.dao.CustomerNodeMapper;
import com.msc.neo4jfirst.dao.CustomerRelationMapper;
import com.msc.neo4jfirst.pojo.CustomerNode;
import com.msc.neo4jfirst.pojo.CustomerRelation;
import com.msc.neo4jfirst.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mSc
 * @version 1.0
 * @Package com.msc.neo4jfirst.service.impl
 * @Description:
 */
@Service
public class GraphServiceImpl implements GraphService {
    @Autowired
    private CustomerNodeMapper customerRepository;
    @Autowired
    private CustomerRelationMapper customerRelationRepository;

    @Override
    public void deleteNodeById(Long id) {
        customerRepository.findNodeById(id);
    }

    @Override
    public void deleteNodeByName(String name) {
        customerRepository.deleteByName(name);
    }

    @Override
    public void delete() {
        customerRepository.deleteAll();
    }

    @Override
    public void addNode(String name, Integer age, String nameTo, String remark) {
        CustomerNode customerNode = new CustomerNode();
        customerNode.setName(name);
        customerNode.setAge(age);
        customerRepository.save(customerNode);
        CustomerNode customerNodeTo = customerRepository.findByName(nameTo);
        if (customerNodeTo != null) {
            CustomerRelation customerRelation =
                    new CustomerRelation(customerNode, customerNodeTo, remark);
            customerRelationRepository.save(customerRelation);
        }
    }

    /**
     * @return void
     * @Author mSc @Description 根据传入值对已有节点进行修改 @Param [id, name, age]
     */
    @Override
    public void updateNode(Long id, String name, Integer age) {
        CustomerNode customerNode = customerRepository.findNodeById(id);
        customerNode.setName(name);
        customerNode.setAge(age);
        customerRepository.save(customerNode);
    }

    /**
     * @return java.lang.Iterable<com.msc.demo.pojo.CustomerNode>
     * @Author mSc @Description 遍历所有节点 @Param []
     */
    @Override
    public Iterable<CustomerNode> queryNodes() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerNode findByName(String name) {
        return customerRepository.findByName(name);
    }

    /**
     * @return java.util.List<com.msc.demo.pojo.CustomerNode>
     * @Author mSc @Description 查找关系节点 @Param [name]
     */
    @Override
    public List<CustomerNode> queryNodes(String name) {
        return customerRepository.findRelationByCustomerNode(name);
    }
}
