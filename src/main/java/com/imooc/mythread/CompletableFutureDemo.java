//package com.imooc.mythread;
//
//import com.google.common.collect.Lists;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//
//public class CompletableFutureDemo {
//    public static void main(String[] args) {
//        Long start = System.currentTimeMillis();
//        //结果集
//        List<String> list = new ArrayList<String>();
//        List<String> list2 = new ArrayList<String>();
//        //定长3线程池
//        ExecutorService exs = Executors.newFixedThreadPool(10);
//        List<CompletableFuture<String>> futureList = new ArrayList<>();
//        List<Integer> taskList = Lists.newArrayList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
//        try {
//            //方式一：循环创建CompletableFuture list,调用sequence()组装返回一个有返回值的CompletableFuture，返回结果get()获取
//            for (int i = 0; i < taskList.size(); i++) {
//                final int j = i + 1;
//                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> calc(j), exs)//异步执行
//                        .thenApply(e -> Integer.toString(e))//Integer转换字符串    thenAccept只接受不返回不影响结果
//                        .whenComplete((v, e) -> {//如需获取任务完成先手顺序，此处代码即可
//                        System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date());
//                            list2.add(v);
//                        });
//                futureList.add(future);
//            }
//            //流式获取结果
//            list = sequence(futureList).get();//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]此处不理解为什么是这样的顺序？谁知道求告知
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            exs.shutdown();
//        }
//    }
//}
