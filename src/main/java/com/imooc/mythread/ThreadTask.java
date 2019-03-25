package com.imooc.mythread;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

@Component
public class ThreadTask {

    @Autowired
    private ProductCategoryRepository repository;

    class ThreadTask1 implements Runnable {


        //    private List<Integer> list;
        private List<ProductCategory> list;
        private CyclicBarrier barrier;
        private Integer id;

        public ThreadTask1(CyclicBarrier barrier, Integer id, List<ProductCategory> list) {
            this.barrier = barrier;
            this.id = id;
            this.list = list;

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
                synchronized (ThreadTask1.class) {
                    ProductCategory productCategory = repository.findOne(id);
                    if (productCategory == null) {
                        System.out.println("报错啦------------");
                    }
                    list.add(productCategory);
                }
                System.out.println("计算出员工" + id + "的工资");
                System.out.println("================================================");
                barrier.await();
//                synchronized (this) {
//                    Thread.sleep(1000);

//                    int i = new Random().nextInt(1000) + 500;
//                     System.out.println("员工"+id+"的工资为："+i);
//                    list.add(i);
//
////                }
//                System.out.println(productCategory.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}