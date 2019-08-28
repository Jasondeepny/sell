package com.imooc;

import com.imooc.dto.CartDTO;
import com.imooc.repository.ProductCategoryRepository;
import com.imooc.utils.RandomUtil;
import com.imooc.utils.SimpleBloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by 廖师兄
 * 2017-06-02 17:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class LoggerTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void test1() {


        System.out.println(repository.findOne(1));


        String name = "imooc";
        String password = "123456";
        log.debug("debug...");
        log.info("name: " + name + " ,password: " + password);
        log.info("name: {}, password: {}", name, password);
        log.error("error...");
        log.warn("warn...");
    }

    @Test
    public void test2() {

//        RandomUtil.buildIntRandom(15);
//        System.out.println(RandomUtil.buildIntRandom(15));
//       // System.out.println(Long.parseLong("1234567890111111"));

//        DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssS");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        //LocalDateTime date = LocalDateTime.parse("20170617122312", formatter);
        //System.out.println(formatter.format(date));
//        System.out.println(StringUtils.rightPad(DATE_FORMAT.format(new Date()), 17, "0"));
//        System.out.println("------------------------------");
//        System.out.println(StringUtils.rightPad(LocalDateTime.now().format(formatter), 17, "0"));
        //System.out.println(new Date().getTime());
        long l = 1000 * 10L;
        System.out.println(l);

    }

    @Test
    public void test() {

        List<String> list = new ArrayList();
        list.add("rw1rwr");
        list.add("rwr2wr");
        list.add("rwrw3r");
        list.add("rwrw5r");
        list.add("rwr5wr");
        list.add("rwr4wr");
        list.add("rw3rwr");
        list.add("rw2rwr");
        list.add("rwrwr");
        list.add("rwr575wr");
        list.add("rwrw74r");
        list.add("rwr4w3r");
        list.add("rwr3wr");
        list.add("rwr33wr");
        SimpleBloomFilter f = new SimpleBloomFilter();
        List arrayList = new ArrayList();
        arrayList.add("shfddj");
        arrayList.add("rw1rwr");
        arrayList.add("rwr2wr");
        arrayList.add("shfddj");
        arrayList.add("shfddj");
        arrayList.add("shfddj");
        f.addAll(arrayList);

        getNewList(list, f);
        System.out.println(list);

        //arrayList.parallelStream().distinct().forEach(System.out::println);


    }

    @Test
    public void dis3() {
        CartDTO c = new CartDTO("1", 2);
        CartDTO c1 = new CartDTO("2", 2);
        CartDTO c2 = new CartDTO("3", 2);
        CartDTO c3 = new CartDTO("4", 2);
        CartDTO c4 = new CartDTO("2", 2);
        CartDTO c5 = new CartDTO("1", 2);
        List<CartDTO> alist = new ArrayList();
        alist.add(c);
        alist.add(c1);
        alist.add(c2);
        alist.add(c3);
        alist.add(c4);
        alist.add(c5);

        alist.parallelStream().filter(distinctByKey(CartDTO::getProductId)).forEach(System.out::println);

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> key) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(key.apply(t));
    }


    private List<String> getNewList(List<String> list, SimpleBloomFilter f) {
        Iterator<String> iterator = list.iterator();
        //通过bloom过滤器去重
        while (iterator.hasNext()) {
            String str = iterator.next();
            boolean row = f.contains(str);
            if (row) {
                iterator.remove();
            }
            f.add(str);
        }
        //返回去重后的list
        return list;
    }


}





