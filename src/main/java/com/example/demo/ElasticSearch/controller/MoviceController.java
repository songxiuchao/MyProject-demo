package com.example.demo.ElasticSearch.controller;

import com.example.demo.ElasticSearch.po.ESDocument;
import com.example.demo.ElasticSearch.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: demo
 * @description: 控制类
 * @author: xiuchao Song
 * @create: 2019-11-04 15:10
 **/
@RestController
@RequestMapping("/elasticSearch")
@Slf4j
public class MoviceController {

    @Autowired
    private ElasticSearchService movieService;

    //https://blog.csdn.net/hezhezhiyule/article/details/82893797

    @PostMapping("/add")
    public void add() {
        movieService.save(new ESDocument(UUID.randomUUID().toString(),"name1","1"));
    }
    @PostMapping("/getById")
    public void getById(){
        movieService.getById("98c717e2-0e17-4887-86f6-e9cd347f97f7");
    }
    @PostMapping("/getByName")
    public void getByName(){
        movieService.getByName("name1","1");
    }
    @DeleteMapping("/delete")
    public void delete(){
        movieService.delete("98c717e2-0e17-4887-86f6-e9cd347f97f7");
    }

}
