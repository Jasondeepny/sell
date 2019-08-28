package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-11 23:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        List<OrderDetail> arrayList = new ArrayList();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810")
                .setOrderId("11111112")
                .setProductIcon("http://xxxx.jpg")
                .setProductId("11111113").setProductName("皮粥").setProductPrice(new BigDecimal(2.2)).setProductQuantity(3);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setDetailId("1234567811")
                .setOrderId("11111113")
                .setProductIcon("http://xxxx.jpg")
                .setProductId("11111112").setProductName("粥").setProductPrice(new BigDecimal(2.2)).setProductQuantity(3);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setDetailId("1234567812")
                .setOrderId("11111113")
                .setProductIcon("http://xxxx.jpg")
                .setProductId("11111114").setProductName("皮蛋粥1").setProductPrice(new BigDecimal(2.2)).setProductQuantity(3);
        OrderDetail orderDetail3 = new OrderDetail();
         orderDetail3.setDetailId("1234567813")
                .setOrderId("11111114")
                .setProductIcon("http://xxxx.jpg")
                .setProductId("11111115").setProductName("皮蛋粥2").setProductPrice(new BigDecimal(2.2)).setProductQuantity(3);
        OrderDetail orderDetail4 = new OrderDetail();
        orderDetail4.setDetailId("1234567814")
                .setOrderId("11111115")
                .setProductIcon(null)
                .setProductId("11111116").setProductName("皮蛋粥3").setProductPrice(new BigDecimal(2.2)).setProductQuantity(3);
        arrayList.add(orderDetail);
        arrayList.add(orderDetail1);
        arrayList.add(orderDetail2);
        arrayList.add(orderDetail3);
        arrayList.add(orderDetail4);
        arrayList.forEach( a ->{
            OrderDetail result = repository.save(orderDetail);
        });

    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = repository.findByOrderId("11111111");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

}