package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import com.imooc.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-05-07 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest() {

        ProductCategory productCategory = new ProductCategory("男生最爱", 1);
        ProductCategory productCategory1 = new ProductCategory("男生最爱5", 2);
        ProductCategory productCategory2 = new ProductCategory("男生最爱3", 3);
        ProductCategory productCategory3 = new ProductCategory("男生最爱12", 4);
        ProductCategory productCategory4 = new ProductCategory("男生最爱21", 5);
        ProductCategory productCategory5 = new ProductCategory("男生最爱2", 6);
        ProductCategory productCategory6 = new ProductCategory(null, 7);
        ProductCategory productCategory13 = new ProductCategory(null, 14);
        ProductCategory productCategory7 = new ProductCategory("男生最爱52", 8);
        ProductCategory productCategory8 = new ProductCategory("男生最", 9);
        ProductCategory productCategory9 = new ProductCategory("男生最爱32", 10);
        ProductCategory productCategory10 = new ProductCategory("男生最爱1e", 11);
        ProductCategory productCategory11 = new ProductCategory("男生最爱d", 12);
        ProductCategory productCategory12 = new ProductCategory("男生最爱ce", 13);
        List<ProductCategory> arrayList = new ArrayList();
        arrayList.add(productCategory);
        arrayList.add(productCategory1);
        arrayList.add(productCategory2);
        arrayList.add(productCategory3);
        arrayList.add(productCategory4);
        arrayList.add(productCategory5);
        arrayList.add(productCategory6);
        arrayList.add(productCategory7);
        arrayList.add(productCategory13);
        arrayList.add(productCategory8);
        arrayList.add(productCategory9);
        arrayList.add(productCategory10);
        arrayList.add(productCategory11);

        arrayList.forEach(p -> {
            try {
                ProductCategory save = categoryService.save(p);
                System.out.println(save);
            } catch (Exception e) {
                System.out.println("------------------------------");
                e.printStackTrace();
            }
        });

        ProductCategory save = categoryService.save(productCategory12);
        System.out.println("++++++++++++++");
        System.out.println(save);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateTest() {
//        ProductCategory productCategory = repository.findOne(4);
//        productCategory.setCategoryName("男生最爱1");
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertEquals(productCategory, result);
    }
}