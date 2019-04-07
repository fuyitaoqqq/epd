package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.ProjectMapMapper;
import cn.animalcity.apidocument.pojo.*;
import cn.animalcity.apidocument.projectenum.ProjectMapEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Service
public class ProjectMapService {

    private final ProjectMapMapper projectMapMapper;

    @Autowired
    public ProjectMapService(ProjectMapMapper projectMapMapper) {
        this.projectMapMapper = projectMapMapper;
    }

    /**
     * 获取ProjectParent子类，插入project map
     *
     * @param instance project module api
     * @return 插入数据的primary key
     */
    public int insert(AbstractProjectParent instance) {
        ProjectMap projectMap = new ProjectMap();
        int pid;

        projectMap.setName(instance.getName());
        projectMap.setUri(instance.getUri());

        if (instance instanceof Project) {
            pid = 0;
            projectMap.setProjectId(((Project) instance).getId());
            projectMap.setPid(pid);
        } else if (instance instanceof Module) {
            pid = getPrimaryKeyByParam(((Module) instance).getProjectId(), null, null);
            projectMap.setModuleId(((Module) instance).getId());
            projectMap.setPid(pid);
        } else if (instance instanceof Api) {
            pid = getPrimaryKeyByParam(null, ((Api) instance).getModuleId(), null);
            projectMap.setApiId(((Api) instance).getId());
            projectMap.setPid(pid);
        }

        return projectMapMapper.insert(projectMap);
    }

    /**
     * 获取project map的id
     *
     * @param projectId project id
     * @param moduleId  module id
     * @param apiId     api id
     * @return primary key of project map
     */
    private int getPrimaryKeyByParam(Integer projectId, Integer moduleId, Integer apiId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        if (projectId != null) {
            wrapper.eq("project_id", projectId);
        } else if (moduleId != null) {
            wrapper.eq("module_id", moduleId);
        } else if (apiId != null) {
            wrapper.eq("api_id", apiId);
        }
        wrapper.select("id");
        return projectMapMapper.selectOne(wrapper).getId();
    }

    /**
     * 根据instance查询在project map中的主键id
     * @param instance project module api
     * @return nstance查询在project map中的主键id
     */
    private int getPrimaryKeyByParent(AbstractProjectParent instance) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();

        if (instance instanceof Project) {
            wrapper.eq("project_id", ((Project) instance).getId());
        } else if (instance instanceof Module) {
            wrapper.eq("module_id", ((Module) instance).getId());
        } else if (instance instanceof Api) {
            wrapper.eq("api_id", ((Api) instance).getId());
        }

        return projectMapMapper.selectOne(wrapper).getId();
    }

    /**
     * 查询ModuleList就传project对象，查询ApiList就传module对象
     * @param instance project module
     * @return List<ProjectMap>
     */
    public List<ProjectMap> getListByPid(AbstractProjectParent instance) {
        int pid = getPrimaryKeyByParent(instance);

        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);

        return projectMapMapper.selectList(wrapper);
    }

    /**
     * 根据pid获取指定list
     * @param pid
     * @return
     */
    public List<ProjectMap> getListByPid(Integer pid) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);

        return projectMapMapper.selectList(wrapper);
    }

    /**
     * 删除模块时，删除module单条记录和下属api记录
     * @param moduleId
     * @param isMapPrimaryKey 判断moduleId是否是project_map表中的主键，还是module表中的主键
     * @return
     */
    int deleteModule(Integer moduleId, boolean isMapPrimaryKey) {
        int modulePrimaryId;
        if (isMapPrimaryKey) {
            modulePrimaryId = moduleId;
        } else {
            modulePrimaryId = getPrimaryKeyByParent(Module.builder().id(moduleId).build());
        }
        List<Integer> ids = Lists.newArrayList();
        for (ProjectMap projectMap : getListByPid(modulePrimaryId)) {
            ids.add(projectMap.getId());
        }
        ids.add(modulePrimaryId);
        return projectMapMapper.deleteBatchIds(ids);
    }

    /**
     * deleteById删除项目单条记录，deleteModule方法删除下属module所有记录
     * @param projectId
     */
    int deleteWholeProject(Integer projectId) {
        int projectPrimaryId = getPrimaryKeyByParam(projectId, null, null);

        List<ProjectMap> moduleList = getListByPid(projectPrimaryId);

        for (ProjectMap module : moduleList) {
            deleteModule(module.getId(), true);
        }

        return projectMapMapper.deleteById(projectPrimaryId);
    }


    /**
     * 更新project map数据
     * @param projectMap 调用方法之前构造的project map
     * @param mapEnum 选择是project module api
     * @param column mapEnum = project需要传uri，mapEnum = module需要传moduleId，mapEnum = api需要传apiId
     * @return
     */
    int updateProjectMap(ProjectMap projectMap, ProjectMapEnum mapEnum, Object column) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();

        switch (mapEnum) {
            case PROJECT:
                wrapper.eq("uri", column);
                break;
            case MODULE:
                wrapper.eq("module_id", column);
                break;
            case API:
                wrapper.eq("api_id", column);
            default:
                break;
        }

        return projectMapMapper.update(projectMap, wrapper);
    }

    /**
     * 根据某个可定位字段删除project map信息，字段如id，project的uri，project_id，module_id，api_id
     * @param column 可用于定位的字段
     * @param value 字段值
     * @return 删除成功返回1
     */
    int deleteProjectMap(String column, Object value) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq(column, value);
        return projectMapMapper.delete(wrapper);
    }

}
