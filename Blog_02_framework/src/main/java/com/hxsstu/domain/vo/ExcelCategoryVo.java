package com.hxsstu.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ExcelCategoryVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-14:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    private String name;
    @ExcelProperty("描述")
    private String description;
    @ExcelProperty("状态 0正常,1禁用")
    private String status;
}
