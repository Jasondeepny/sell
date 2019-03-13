package com.imooc.mythread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class AskTask implements Runnable {
    private CyclicBarrier barrier;

    private final List<Result> results;

    AskTask(CyclicBarrier barrier, List<Result> results) {
        this.barrier = barrier;
        this.results = results;
    }

    @Override
    public void run() {
        Result result = null;
        try {
            // 模拟网络耗时获取数据
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));

            result = new Result();
            result.setPrice((new Random().nextInt(1000) + 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        synchronized (AskTask.class) {
            if (result != null) {
                /* 模拟报价站点名称 */
                String[] sites = {"苏宁", "天猫", "京东"};
                result.setSite(sites[results.size()]);
                System.out.println(sites[results.size()]);
                // 添加到共享变量list中
                results.add(result);
            }
//        }
        try {
            System.out.println("询价结束，等待其它线程..." + result);
            // 抵达屏障
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final List<Result> results = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(3,
                new Runnable() {
                    @Override
                    public void run() {
                        if (!results.isEmpty()) {
                            Result min = results.get(0);
                            for (Result result : results) {
                                if (result.getPrice() < min.getPrice()) min = result;
                            }
                            System.out.println("最低报价是：" + min);
                        }
                    }
                });
        // 模拟三个询价接口，采用线程池管理
        for (int i = 0; i < 3; i++) {
            AskTask c = new AskTask(barrier, results);
            service.submit(c);
        }
        service.shutdown();
    }
}
