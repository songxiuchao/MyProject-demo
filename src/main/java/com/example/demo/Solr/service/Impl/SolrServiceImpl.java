package com.example.demo.Solr.service.Impl;

import com.example.demo.Solr.po.User;
import com.example.demo.Solr.service.SolrService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-15 16:53
 **/
@Service("solrService")
public class SolrServiceImpl implements SolrService {
    @Override
    public List<User> addUser() {
        List<User> list = new ArrayList<>();
        User user = new User();
        for (int i = 0; i <5 ; i++) {
            user.setName("宋修超");
            user.setSex("男");
            user.setAddress("QD");
            user.setHost(6);
            list.add(user);
        }
        return list;
    }
}