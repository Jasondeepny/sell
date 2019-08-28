package com.imooc.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhouyanyang
 * @Date: 2019-08-18 00:45
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener {
    private List<Object> data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        data.add(object);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }
    public void doSomething(){
        for (Object o:data) {
            System.out.println(o);
        }
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
