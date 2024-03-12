package com.yh.manage.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.domain.cms.response.CmsPageResult;
import com.lxw.framework.model.response.CommonCode;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.QueryResult;
import com.lxw.framework.model.response.ResponseResult;
import com.yh.manage.dao.CmsPageRepository;
import com.yh.manage.service.CmsPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class CmsPageServiceImpl implements CmsPageService {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        //首页不显示
        if (page <=0){
            page=1;
        }
        page=page -1;
        //设置分页
        PageRequest pageRequest = new PageRequest(page, size);
        //设置条件 浅拷贝
        CmsPage cmsPage = new CmsPage();
        BeanUtils.copyProperties(queryPageRequest, cmsPage);
        //条件匹配器
        ExampleMatcher matching = ExampleMatcher.matching();
        //修改匹配规则 模糊匹配
        matching.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> cmsPageExample = Example.of(cmsPage, matching);

        //执行分页
        Page<CmsPage> all = cmsPageRepository.findAll(cmsPageExample, pageRequest);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        CmsPage cmsPageDb = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getPageWebPath(),
                cmsPage.getSiteId());
        if (cmsPageDb == null){
            cmsPage.setPageId(null);
            cmsPageRepository.insert(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL, null);

    }

    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        return byId.orElse(null);
    }

    //根据id修改
    @Override
    public CmsPageResult update(String id, CmsPage cmsPage) {
        CmsPage byId = findById(id);
        if (byId != null) {
            //更新模板id
            byId.setTemplateId(cmsPage.getTemplateId());
            byId.setPageName(cmsPage.getPageName());
            byId.setPageWebPath(cmsPage.getPageWebPath());
            byId.setPageParameter(cmsPage.getPageParameter());
            byId.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            byId.setPageCreateTime(cmsPage.getPageCreateTime());
            byId.setPageType(cmsPage.getPageType());
            byId.setPageStatus(cmsPage.getPageStatus());
            byId.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            byId.setDataUrl(cmsPage.getDataUrl());
            cmsPageRepository.save(byId);
                return new CmsPageResult(CommonCode.SUCCESS, byId);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    @Override
    public ResponseResult delete(String id) {
        CmsPage byId = findById(id);
        if (byId != null) {
            cmsPageRepository.delete(byId);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
