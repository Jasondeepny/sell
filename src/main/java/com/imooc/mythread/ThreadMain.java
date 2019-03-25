package com.imooc.mythread;

import com.imooc.dataobject.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {

    public static void main(String[] args) {
        //多线程计算结果
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final List<ProductCategory> list = new ArrayList<>();

        //定义一个循环屏障，在屏障线程中进行计算结果合并
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            if(!list.isEmpty()){
                for (ProductCategory productCategory : list) {
                    System.out.println(productCategory.toString()+"---------------------------------");
                }
            }
//            int sum = 0 ;
//            for (Integer e : list) {
//                sum =  sum +e;
//            }
//            System.out.println(sum);

            System.out.println("===========================工资计算完毕==============================");
        });

        for (int i = 1; i < 5; i++) {

            executorService.submit(new ThreadTask().new ThreadTask1(barrier, i,list));
        }
        executorService.shutdown();

    }
}
