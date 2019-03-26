package com.imooc;

import com.imooc.repository.ProductCategoryRepository;
import com.imooc.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Date;
import java.util.Random;

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

        DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssS");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        //LocalDateTime date = LocalDateTime.parse("20170617122312", formatter);
        //System.out.println(formatter.format(date));
//        System.out.println(StringUtils.rightPad(DATE_FORMAT.format(new Date()), 17, "0"));
//        System.out.println("------------------------------");
//        System.out.println(StringUtils.rightPad(LocalDateTime.now().format(formatter), 17, "0"));
          System.out.println(new Date().getTime());



    }




}





