package com.example.demo.pay.pay.service;

import com.example.demo.Common.constant.Result;

import java.util.SortedMap;

public interface PayService {

    Result pay(SortedMap<Object, Object> bizMap);
}
