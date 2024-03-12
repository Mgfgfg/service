package com.yh.manage.service;

import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.ResponseResult;
import org.springframework.stereotype.Service;
import com.lxw.framework.domain.cms.response.CmsPageResult;

public interface CmsPageService {
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    CmsPageResult add(CmsPage cmsPage);

    CmsPage findById(String id);

    CmsPageResult update(String id,CmsPage cmsPage);

    ResponseResult delete(String id);
}
