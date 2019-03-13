//package com.imooc.mythread;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//@Component
//public class BookStatisticTask {
//
//    @Autowired
//    private BookThread bookThread;
//    @Autowired
//    ThreadPoolTaskExecutor taskExecutor;
//
//
//    public List<BookStatistic> getPsrList() {
//        List<BookStatistic> psrList = new ArrayList<BookStatistic>();
//        for (int i = 0; i < 20000; i++) {
//            BookStatistic book = new BookStatistic();
//            book.setPno("zxl" + i);
//            psrList.add(book);
//        }
//        return psrList;
//    }
//
//    public void ReceivePsrJobRun(){
//        List<BookStatistic> bookList = null;
//        bookList = getPsrList();
//        //接收集合各段的 执行的返回结果
//        List<Future<String>> futureList = new ArrayList<Future<String>>();
//        //集合总条数
//        int size = bookList.size();
//        //将集合切分的段数(2*CPU的核心数)
//        int sunSum = 2*Runtime.getRuntime().availableProcessors();
//        int listStart,listEnd;
//        //当总条数不足sunSum条时 用总条数 当做线程切分值
//        if(sunSum > size){
//            sunSum = size;
//        }
//
//        //定义子线程
//        /*BookThread bookThread;*/
//
//        //将list 切分多份 多线程执行
//        for (int i = 0; i < sunSum; i++) {
//            //计算切割  开始和结束
//            listStart = size / sunSum * i ;
//            listEnd = size / sunSum * ( i + 1 );
//            //最后一段线程会 出现与其他线程不等的情况
//            if(i == sunSum - 1){
//                listEnd = size;
//            }
//            //线程切断**/
//            List<BookStatistic> sunList = bookList.subList(listStart,listEnd);
//            //子线程初始化
//            bookThread = new BookThread(i,sunList);
//
//            //多线程执行
//            futureList.add(taskExecutor.submit(bookThread));
//        }
//        System.out.println("----------1111111111");
//
//        //对各个线程段结果进行解析
//        for(Future<String> future : futureList){
//            try {
//                String str ;
//                if(null != future ){
//                    str = future.get().toString();
//                    System.out.println("##############current thread id ="+Thread.currentThread().getName()+",result="+str);
//                }else{
//                    System.err.println("失败");
//                }
//            } catch (InterruptedException | ExecutionException e) {
//
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//
//            }
//        }
//        System.out.println("----------2222");
//
//    }
//
//}
