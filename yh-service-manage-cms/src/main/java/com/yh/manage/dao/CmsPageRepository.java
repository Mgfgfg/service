package com.yh.manage.dao;

import com.lxw.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
    //根据页面名称、站点id、页面访问路径查询
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

}
