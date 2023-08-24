package com.hxsstu.domain.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * ClassName: MenuVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-19:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    private Long id;
    private String menuName;
    private Long parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isFrame;
    private String menuType;
    private String visible;
    private String status;
    private String icon;
    private Date createTime;
    private List<MenuVo> children;

}
