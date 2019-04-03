package cn.ibeaver.service;

import cn.ibeaver.dao.ApiMapper;
import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.pojo.Api;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: fuyitao
 * @date: 2019/04/02
 */

@Service
public class ApiService extends ServiceImpl<ApiMapper, Api> {

    @Autowired
    private ApiMapper apiMapper;

    @Autowired
    private ProjectMapService projectMapService;

    public Api insertApi(Api api, Integer projectId, Integer moduleId, SysUser user) {
        api.setProjectId(projectId);
        api.setModuleId(moduleId);
        api.setCreateBy(user.getId());
        api.setCreateByName(user.getLoginName());
        api.setCreateTime(new Date());
        apiMapper.insert(api);

        ProjectMap byModuleId = projectMapService.getByModuleId(moduleId);
        ProjectMap projectMap = new ProjectMap();
        projectMap.setName(api.getName()).setUri(api.getName())
                .setApiId(api.getId()).setPid(byModuleId.getId());
        projectMapService.insert(projectMap);

        return api;
    }

    public int deleteApi(Integer projectId, Integer moduleId, Integer apiId) {

        projectMapService.deleteApiByModuleId(projectId, moduleId, apiId);

        return apiMapper.deleteById(apiId);

    }

    public int updateApi(Api api, SysUser user) {

        api.setUpdateBy(user.getId());
        api.setUpdateByName(user.getLoginName());
        api.setUpdateTime(new Date());
        int j = apiMapper.updateById(api);

        Api selectById = apiMapper.selectById(api.getId());

        ProjectMap apiByModuleIdAndApiId = projectMapService.getApiByModuleIdAndApiId(selectById.getProjectId(), selectById.getModuleId(), selectById.getId());
        apiByModuleIdAndApiId.setName(selectById.getName());
        apiByModuleIdAndApiId.setUri(selectById.getUri());
        int i = projectMapService.updateById(apiByModuleIdAndApiId);

        if (i == 0 && j == 0) {
            return ResultContants.SYS_ERR.getCode();
        }
        return ResultContants.SUCCESS.getCode();

    }

    public Api getApiDetail(Integer apiId) {
        return apiMapper.selectById(apiId);
    }
}
