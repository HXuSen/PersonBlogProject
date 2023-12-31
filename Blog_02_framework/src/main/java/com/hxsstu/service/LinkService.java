package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.LinkDto;
import com.hxsstu.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-08-20 15:49:57
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult linkList(Integer pageNum, Integer pageSize, LinkDto linkDto);

    ResponseResult addLink(LinkDto linkDto);

    ResponseResult getLinkById(Long id);

    ResponseResult updateLinkById(LinkDto linkDto);
}

