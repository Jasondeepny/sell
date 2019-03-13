package com.imooc.mythread;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ProductTask implements Runnable {


    @Autowired
    private ProductCategoryRepository repository;

    private CyclicBarrier barrier;

    private final List<ProductCategory> results;

    ProductTask(CyclicBarrier barrier, List<ProductCategory> results) {
        this.barrier = barrier;
        this.results = results;
    }

    @Override
    public void run() {

        ProductCategory productCategory = null;








    }
}
