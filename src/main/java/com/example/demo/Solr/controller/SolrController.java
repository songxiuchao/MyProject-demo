package com.example.demo.Solr.controller;

import com.example.demo.Solr.service.SolrService;
import com.example.demo.Solr.po.User;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-15 16:55
 **/
@RestController
@RequestMapping("/solr")
public class SolrController {
    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient solrClient;

    //批量增加
    @RequestMapping("/addUsers")
    public void addUsers() throws IOException, SolrServerException {
        List<User> users = solrService.addUser();

        solrClient.addBeans(users);
        solrClient.commit();
    }
    //单个增加
    @RequestMapping("/addUser")
    public void addUser() throws IOException, SolrServerException {
        User user = new User();
        user.setName("宋修超");
        user.setAddress("青岛");
        solrClient.addBean(user);
        solrClient.commit();
    }
    //根据id查询
    @RequestMapping("/getByIdFromSolr/{id}")
    public  void getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {

        //根据id查询内容
        SolrDocument solrDocument = solrClient.getById(id);
        //获取filedName
        Collection<String> fieldNames = solrDocument.getFieldNames();
        //获取file名和内容
        Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();

        List<SolrDocument> childDocuments = solrDocument.getChildDocuments();
        System.out.println("byId=================="+solrDocument);
        System.out.println("fieldNames=================="+fieldNames);
        System.out.println("fieldValueMap=================="+fieldValueMap);
        System.out.println("childDocuments=================="+childDocuments);

    }
    //根据di删除
    @RequestMapping("/delById/{id}")
    public  void  delById(@PathVariable("id") String id) throws IOException, SolrServerException {
        //根据id删除信息
        UpdateResponse updateResponse = solrClient.deleteById(id);
        //执行的时间
        long elapsedTime = updateResponse.getElapsedTime();

        int qTime = updateResponse.getQTime();
        //请求地址
        String requestUrl = updateResponse.getRequestUrl();
        //请求的结果{responseHeader={status=0,QTime=2}}
        NamedList<Object> response = updateResponse.getResponse();
        //请求结果的头{status=0,QTime=2}
        NamedList responseHeader = updateResponse.getResponseHeader();
        //请求的状态 0
        int status = updateResponse.getStatus();

        System.out.println("elapsedTime==========="+elapsedTime);
        System.out.println("qTime==========="+qTime);
        System.out.println("requestUrl==========="+requestUrl);
        System.out.println("response==========="+response);
        System.out.println("responseHeader==========="+responseHeader);
        System.out.println("status==========="+status);
    }

    @RequestMapping("/queryFromSolr")
    public  Object  queryFromSolr() throws IOException, SolrServerException {
        //第一种方式
//        Map<String, String> queryParamMap = new HashMap<String, String>();
//        queryParamMap.put("q", "*:*");
//        queryParamMap.put("f1","id,name");
//        queryParamMap.put("sort","id asc");
//        MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
//        solrClient.query(mapSolrParams);

        //第二种方式
        SolrQuery solrQuery  = new SolrQuery();
        solrQuery.setQuery("*:*");
//        solrQuery.addField("*");
        solrQuery.add("q","id:4567");

        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        //设置查询的条数
        solrQuery.setRows(50);
        //设置查询的开始
        solrQuery.setStart(0);
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮的字段
        solrQuery.addHighlightField("item_name");
        //设置高亮的样式
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        System.out.println(solrQuery);
        QueryResponse response = solrClient.query(solrQuery);
        //返回高亮显示结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //response.getResults();查询返回的结果
        SolrDocumentList documentList = response.getResults();
        for (SolrDocument solrDocument : documentList) {
            System.out.println("solrDocument==============" +solrDocument);
        }
        return documentList;
    }
}