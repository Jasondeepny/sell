package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
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
public class ProductCategoryRepositoryTest03 {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest01() throws InterruptedException, ExecutionException {
        //多线程计算结果
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //起多个任务
        List<Callable<ProductCategory>> tasks = new ArrayList<Callable<ProductCategory>>();

        Callable<ProductCategory> task = getProductCategoryCallable(1);
        tasks.add(task);
        Callable<ProductCategory> task1 = getProductCategoryCallable(2);
        tasks.add(task1);
        Callable<ProductCategory> task2 = getProductCategoryCallable(3);
        tasks.add(task2);
        Callable<ProductCategory> task3 = getProductCategoryCallable(4);
        tasks.add(task3);

        List<Future<ProductCategory>> results = executorService.invokeAll(tasks);
        results.forEach(future -> {
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private Callable<ProductCategory> getProductCategoryCallable(int i) {
        return new Callable<ProductCategory>() {
            @Override
            public ProductCategory call() throws Exception {
                return repository.findOne(i);
            }
        };
    }
}
