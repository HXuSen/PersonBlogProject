package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.LinkDto;
import com.hxsstu.domain.entity.Link;
import com.hxsstu.domain.vo.LinkVo;
import com.hxsstu.domain.vo.PageVo;
import com.hxsstu.mapper.LinkMapper;
import com.hxsstu.service.LinkService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-08-20 15:49:59
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult linkList(Integer pageNum, Integer pageSize, LinkDto linkDto) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.hasText(linkDto.getName()),Link::getName,linkDto.getName());
        queryWrapper.eq(StringUtils.hasText(linkDto.getStatus()),Link::getStatus,linkDto.getStatus());

        Page<Link> page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(),page.getTotal()));
    }

    @Override
    public ResponseResult addLink(LinkDto linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLinkById(Long id) {
        Link link = getById(id);
        LinkVo linkVo = BeanCopyUtils.copyBean(link, LinkVo.class);
        return ResponseResult.okResult(linkVo);
    }

    @Override
    public ResponseResult updateLinkById(LinkDto linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }
}

