package com.lxw.api.cms;

import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分页查询
 */
@Api(tags = "cms crud")
public interface CmsPageControllerApi {
    @ApiOperation("根据站点id、模板id、页面查询信息分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "queryPageRequest", value = "查询条件", required = true, dataType = "queryPageRequest", paramType = "query"),
            @ApiImplicitParam()
    })


    public QueryResponseResult findList(Integer page, Integer size, QueryPageRequest queryPageRequest);

    @ApiOperation("添加页面")
    public ResponseResult add(CmsPage cmsPage);

    @ApiOperation("根据id查询页面")
    public CmsPage findById(String id);
    @ApiOperation("修改页面")
    public ResponseResult update(String id,CmsPage cmsPage);
    @ApiOperation("删除页面")
    public ResponseResult delete(String id);
}