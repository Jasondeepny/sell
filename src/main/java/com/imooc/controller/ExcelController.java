package com.imooc.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.imooc.VO.ResultVO;
import com.imooc.dto.ImportProductDTO;
import com.imooc.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhouyanyang
 * @Date: 2019-08-18 23:04
 * @Description:
 */

@RestController
@RequestMapping("/excel")
public class ExcelController {

    final Set<ImportProductDTO> set = new HashSet<>();

    @PostMapping("/uploadData")
    public ResultVO uploadData(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, ImportProductDTO.class), excelListener);
        List<Object> datas = excelListener.getData();
        List<ImportProductDTO> productDTOList = (List) datas;
        List<ImportProductDTO> arrayList = new ArrayList();
        List<ImportProductDTO> productDTOS = productDTOList.stream().filter(i -> !set.contains(i)).collect(Collectors.toList());
        for (ImportProductDTO imDTO : productDTOS) {
            if (StringUtils.isAnyBlank(imDTO.getTitle(), imDTO.getUnit(), imDTO.getCode(), imDTO.getTireRim(), imDTO.getTireFigure(), imDTO.getTireSpec())
                    || imDTO.getBrandId() == null || imDTO.getCategoryId() == null || imDTO.getMarketPrice() == null || imDTO.getSalePrice() == null) {
                arrayList.add(imDTO);
            }else {
                set.add(imDTO);
            }
        }
        inputStream.close();
        return ResultVOUtil.success(arrayList);
    }
}

