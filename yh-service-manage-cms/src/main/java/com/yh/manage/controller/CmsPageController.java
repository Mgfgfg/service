package com.yh.manage.controller;

import com.lxw.api.cms.CmsPageControllerApi;
import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.ResponseResult;
import com.yh.manage.service.impl.CmsPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cms/page/")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private CmsPageServiceImpl cmsServiceImpl;
    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable Integer page, @PathVariable Integer size,  QueryPageRequest queryPageRequest) {
        QueryResponseResult list = cmsServiceImpl.findList(page, size, queryPageRequest);
        return list;
    }

    @PostMapping("/add")
    @Override
    public ResponseResult add(@RequestBody CmsPage cmsPage) {

        return  cmsServiceImpl.add(cmsPage);
    }

    //根据id查询cms信息
    @Override
    @GetMapping("/findById/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsServiceImpl.findById(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseResult update(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        System.out.println(id);
        return cmsServiceImpl.update(id, cmsPage);
    }

    @GetMapping("/delete/{id}")
    @Override
    public ResponseResult delete(@PathVariable("id") String id) {
        return cmsServiceImpl.delete(id);

    }
}
