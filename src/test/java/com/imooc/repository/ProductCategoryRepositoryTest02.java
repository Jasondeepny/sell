package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductCategoryRepositoryTest02 {
    //多线程计算结果
    //定义线程池
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    //定义五个Future去保存子数组计算结果
    final int[] results = new int[5];
    final List<ProductCategory> list = new ArrayList();
    final CountDownLatch countDownLatch = new CountDownLatch(4);




}
