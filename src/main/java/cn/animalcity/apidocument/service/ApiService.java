package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.ApiMapper;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.pojo.Api;
import cn.animalcity.apidocument.pojo.Module;
import cn.animalcity.apidocument.pojo.ProjectMap;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.projectenum.ProjectMapEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Service
public class ApiService {

    private final ApiMapper apiMapper;

    private final ProjectMapService projectMapService;

    private final ProjectService projectService;

    private final ModuleService moduleService;

    @Autowired
    public ApiService(ApiMapper apiMapper, ProjectMapService projectMapService,
                      ProjectService projectService, ModuleService moduleService) {
        this.apiMapper = apiMapper;
        this.projectMapService = projectMapService;
        this.projectService = projectService;
        this.moduleService = moduleService;
    }

    public List<Api> getApiIdsByModuleId(Integer moduleId) {
        QueryWrapper<Api> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("module_id", moduleId);
        return apiMapper.selectList(wrapper);
    }

    public Api addApi(Api api, SysUser user) {
        api.setCreateBy(user.getId());
        api.setCreateByName(user.getLoginName());
        api.setCreateTime(new Date());
        api.setShowUri(generateShowUri(api));
        int i = apiMapper.insert(api);
        int j = projectMapService.insert(api);
        if (i != 0 && j != 0) {
            return api;
        }
        return null;
    }

    public int deleteApi(Integer apiId) {
        int i = projectMapService.deleteProjectMap("api_id", apiId);
        int j = apiMapper.deleteById(apiId);

        if (i != 0 && j != 0) {
            return ResultConstants.SUCCESS.getCode();
        }
        return ResultConstants.SYS_ERR.getCode();
    }

    public Api updateApi(Api api, SysUser user) {
        api.setUpdateBy(user.getId());
        api.setUpdateByName(user.getLoginName());
        api.setUpdateTime(new Date());
        Api oldApi = apiMapper.selectById(api.getId());
        api.setShowUri(generateShowUri(oldApi));
        int i = apiMapper.updateById(api);

        ProjectMap projectMap = new ProjectMap();
        projectMap.setName(api.getName());
        projectMap.setUri(api.getUri());
        int j = projectMapService.updateProjectMap(projectMap, ProjectMapEnum.API, api.getId());

        if (i != 0 && j != 0) {
            return api;
        }
        return null;
    }

    public Api getApi(Integer apiId) {
        return apiMapper.selectById(apiId);
    }

    private String generateShowUri(Api api) {

        StringBuffer showUri = new StringBuffer();

        String baseUrl = projectService.getBaseUrl(api.getProjectId());

        if (StringUtils.isNotBlank(baseUrl)) {
            showUri.append("/").append(baseUrl);
        }

        Module module = moduleService.getModule(api.getModuleId());

        if (StringUtils.isNotBlank(module.getUri())) {
            showUri.append("/").append(module.getUri());
        }

        if (StringUtils.isNotBlank(api.getUri())) {
            showUri.append("/").append(api.getUri());
        }

        String string = showUri.toString();

        if (string.contains("//")) {
            string = string.replace("//", "/");
        }

        return string;
    }

}
