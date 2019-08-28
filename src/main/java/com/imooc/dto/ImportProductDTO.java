package com.imooc.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ImportProductDTO extends BaseRowModel {

    @ExcelProperty(value = "商品编号",index = 0)
    @NotNull
    private String code ;
    @NotNull
    @ExcelProperty(value = "商品标题",index = 1)
    private String title;
    @NotNull
    @ExcelProperty(value = "品牌id",index = 2)
    private Long brandId;
    @NotNull
    @ExcelProperty(value = "分类id",index = 3)
    private Long categoryId;
    @NotNull
    @ExcelProperty(value = "单位",index = 4)
    private String unit;
    @NotNull
    @ExcelProperty(value = "重量kg",index = 5)
    private BigDecimal weight;
    @NotNull
    @ExcelProperty(value = "零售价",index = 6)
    private BigDecimal salePrice;
    @NotNull
    @ExcelProperty(value = "市场价",index = 7)
    private BigDecimal marketPrice;
    @NotNull
    @ExcelProperty(value = "成本价",index = 8)
    private BigDecimal costPrice;
    @NotNull
    @ExcelProperty(value = "税率",index = 9)
    private BigDecimal taxRate;
    @NotNull
    @ExcelProperty(value = "条形码",index = 10)
    private String barcode;
    @NotNull
    @ExcelProperty(value = "轮胎商品胎面宽",index = 11)
    private String tireWidth;
    @NotNull
    @ExcelProperty(value = "轮胎商品扁平率",index = 12)
    private String tireAspectRatio;
    @NotNull
    @ExcelProperty(value = "轮胎商品直径",index =13)
    private String tireRim;
    @NotNull
    @ExcelProperty(value = "轮胎商品花纹",index = 14)
    private String tireFigure;
    @NotNull
    @ExcelProperty(value = "轮胎商品层级",index = 15)
    private String tireLayer;
    @NotNull
    @ExcelProperty(value = "轮胎商品规格",index = 16)
    private String tireSpec;

    @ExcelProperty(value = "负荷指数",index = 17)
    private String tireLoad;

    @ExcelProperty(value = "速度级别",index = 18)
    private String tireSpeed;


    @Override
    public String toString() {
        return "ImportProductDTO{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                ", salePrice=" + salePrice +
                ", marketPrice=" + marketPrice +
                ", costPrice=" + costPrice +
                ", taxRate=" + taxRate +
                ", barcode='" + barcode + '\'' +
                ", tireWidth='" + tireWidth + '\'' +
                ", tireAspectRatio='" + tireAspectRatio + '\'' +
                ", tireRim='" + tireRim + '\'' +
                ", tireFigure='" + tireFigure + '\'' +
                ", tireLayer='" + tireLayer + '\'' +
                ", tireSpec='" + tireSpec + '\'' +
                ", tireLoad='" + tireLoad + '\'' +
                ", tireSpeed='" + tireSpeed + '\'' +
                '}';
    }
}
