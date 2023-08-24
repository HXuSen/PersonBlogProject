package com.hxsstu.domain.vo;

import com.hxsstu.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: RoutersVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
