package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.ModuleMapper;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.pojo.Api;
import cn.animalcity.apidocument.pojo.Module;
import cn.animalcity.apidocument.pojo.ProjectMap;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.projectenum.ProjectMapEnum;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Service
public class ModuleService {

    private final ModuleMapper moduleMapper;

    private final ProjectMapService projectMapService;

    private final ApiService apiService;

    public ModuleService(ModuleMapper moduleMapper, ProjectMapService projectMapService, ApiService apiService) {
        this.moduleMapper = moduleMapper;
        this.projectMapService = projectMapService;
        this.apiService = apiService;
    }

    public Module addModule(Module module, SysUser user) {
        module.setCreateTime(new Date());
        module.setCreateBy(user.getId());
        module.setCreateByName(user.getLoginName());
        int i = moduleMapper.insert(module);

        int j = projectMapService.insert(module);

        if (i != 0 && j != 0) {
            return module;
        } else {
            return null;
        }
    }

    /**
     * 删除module时要删除下属所有api子项
     * @param moduleId moduleId
     * @return 成功返回0，失败返回-3
     */
    public int deleteModule(Integer moduleId) {

        /**
         * 删除project map中的记录
         */
        int i = projectMapService.deleteModule(moduleId, false);

        List<Integer> ids = Lists.newArrayList();
        List<Api> apiList = apiService.getApiIdsByModuleId(moduleId);

        apiList.forEach(item -> ids.add(item.getId()));

        ids.add(moduleId);
        int j = moduleMapper.deleteBatchIds(ids);

        if (i != 0 && j != 0) {
            return ResultConstants.SUCCESS.getCode();
        }
        return ResultConstants.SYS_ERR.getCode();
    }

    /**
     * 更新module数据
     * @param module 新的module
     * @param user 更新用户
     * @return 新的module
     */
    public Module updateModule(Module module, SysUser user) {
        module.setUpdateBy(user.getId());
        module.setUpdateByName(user.getLoginName());
        module.setUpdateTime(new Date());
        int i = moduleMapper.updateById(module);

        ProjectMap projectMap = new ProjectMap();
        projectMap.setName(module.getName());
        projectMap.setUri(module.getUri());
        int j = projectMapService.updateProjectMap(projectMap, ProjectMapEnum.MODULE, module.getId());

        if (i != 0 && j != 0) {
            return moduleMapper.selectById(module.getId());
        }
        return null;
    }

    public Module getModule(Integer moduleId) {
        Module module = moduleMapper.selectById(moduleId);
        if (module != null) {
            return module;
        } else {
            return null;
        }
    }
}
