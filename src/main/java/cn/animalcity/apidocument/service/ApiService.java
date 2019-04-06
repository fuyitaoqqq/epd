package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.ApiMapper;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.pojo.Api;
import cn.animalcity.apidocument.pojo.ProjectMap;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.projectenum.ProjectMapEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    public ApiService(ApiMapper apiMapper, ProjectMapService projectMapService) {
        this.apiMapper = apiMapper;
        this.projectMapService = projectMapService;
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

}
