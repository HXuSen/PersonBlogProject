package com.hxsstu.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: TagListDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {
    private String name;
    private String remark;
}
