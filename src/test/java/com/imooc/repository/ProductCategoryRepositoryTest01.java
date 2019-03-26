package com.imooc.repository;

import com.google.common.collect.Lists;
import com.imooc.dataobject.ProductCategory;
import com.imooc.service.impl.TestImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest01 {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private TestImpl test;

    private List<ProductCategory> list = new ArrayList<>();

//    private  List<Integer> list = new ArrayList();

      Integer[] arr = {1,2,3,4};

    @Test
    public void findOneTest01() {
        //多线程计算结果
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //定义一个循环屏障，在屏障线程中进行计算结果合并
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            if(!list.isEmpty()){
                for (ProductCategory productCategory : list) {
                    System.out.println(productCategory.toString()+"---------------------------------");
                }
            };
//            int sum = 0 ;
//            for (Integer e : list) {
//                sum =  sum +e;
//            }
//            System.out.println(sum);

            System.out.println("===========================工资计算完毕==============================");
        });

        ArrayList lists = Lists.newArrayList(arr);
        for (int i = 1; i < 5; i++) {
            executorService.submit(new ThreadTask(barrier,lists, i, this.list));
        }
        executorService.shutdown();
    }

    class ThreadTask implements Runnable {

//      private List<Integer> list;
        private List<ProductCategory> list;
        private CyclicBarrier barrier;
        private Integer id;
        private List<Integer> listss;

        public ThreadTask(CyclicBarrier barrier,List<Integer> listss, Integer id, List<ProductCategory> list) {
            this.barrier = barrier;
            this. id = id;
            this.list = list;
            this.listss = listss;

        }
//        public ThreadTask(CyclicBarrier barrier, Integer id, List<Integer> list) {
//            this.barrier = barrier;
//            this. id = id;
//            this.list = list;
//
//        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName());
            try {
                List<ProductCategory> productCategories = repository.findByCategoryTypeIn(listss);

                System.out.println("显示结果为："+productCategories);

//                ProductCategory productCategory = repository.
////                    if (productCategory == null) {
////                        System.out.println("报错啦------------");
////                    }
////                        list.add(productCategory);
//
                System.out.println("计算出员工"+id+"的工资");

                System.out.println("================================================");
                barrier.await();
//
//                    Thread.sleep(1000);

//                    int i = new Random().nextInt(1000) + 500;
//                     System.out.println("员工"+id+"的工资为："+i);
//                    list.add(i);
//
////
//                System.out.println(productCategory.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}



