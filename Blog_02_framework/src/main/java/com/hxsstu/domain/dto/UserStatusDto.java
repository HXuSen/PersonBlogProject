package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserStatusDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/24-11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusDto {
    private Long userId;
    private String status;
}
