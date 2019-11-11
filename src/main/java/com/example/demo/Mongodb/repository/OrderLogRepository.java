package com.example.demo.Mongodb.repository;

import com.example.demo.Mongodb.po.OrderLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends MongoRepository<OrderLog, String> {

}