//package com.imooc.mythread;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.Callable;
//
//@Component
//public class BookThread implements Callable<String > {
//
//    private static final Logger LOG = LoggerFactory.getLogger(BookThread.class);
//    //当前是属于第几段线程
//    private int pageIndex;
//    //此段数据的集合
//    private List<BookStatistic> bookList;
//
//    public BookThread(int pageIndex, List<BookStatistic> bookList) {
//        this.pageIndex = pageIndex;
//        this.bookList = bookList;
//    }
//    @Override
//    public String  call() throws Exception {
//        System.err.println(String.format("此批数据的段数为:%s 此段数据的数据条数为:%s",pageIndex,psrList.size()));
//       // Boolean result = Boolean.TRUE;
//
//        if(null != bookList&& bookList.size() >0){
//            for(BookStatistic book: bookList){
//
//                try {
//                    //数据入库函数
//                } catch (Exception e) {
//
//                   // result = Boolean.FALSE;
//                    continue;
//                }
//            }
//        }
//       // return result;
//    }
//
//}
