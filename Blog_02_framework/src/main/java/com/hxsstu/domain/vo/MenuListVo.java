package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * ClassName: MenuListVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-15:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuListVo {
    private Long id;
    private String menuName;
    private Long parentId;
    private Integer orderNum;
    private String path;
    private Integer isFrame;
    private String menuType;
    private String visible;
    private String status;
    private String icon;
    private String component;
    private Date createTime;
    private String perms;
    private String remark;
}
