package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: TagVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-10:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    private Long id;
    private String name;
    private String remark;
}
