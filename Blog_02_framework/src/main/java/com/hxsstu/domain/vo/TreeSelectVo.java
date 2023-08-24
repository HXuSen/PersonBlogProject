package com.hxsstu.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ClassName: TreeSelectVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TreeSelectVo {
    private Long id;
    private String label;
    private Long parentId;
    List<TreeSelectVo> children;
}
