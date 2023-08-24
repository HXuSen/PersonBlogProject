package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: RoleMenuLoadVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuLoadVo {
    private List<TreeSelectVo> menus;
    private List<String> checkedKeys;
}
