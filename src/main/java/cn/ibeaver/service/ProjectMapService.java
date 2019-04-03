package cn.ibeaver.service;

import cn.ibeaver.dao.ProjectMapMapper;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.pojo.ProjectMap;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: welcome
 * @date: 2019/03/31
 */

@Service
public class ProjectMapService {

    @Autowired
    private ProjectMapMapper projectMapMapper;

    public List<ProjectMap> getByPid(String select, Integer pid) {

        switch (select) {
            case "module":
                select = "module_id";
                break;
            case "api":
                select = "api_id";
                break;
            default:
                select = "module_id";
        }

        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "uri", select);
        wrapper.eq("pid", pid);
        return projectMapMapper.selectList(wrapper);
    }

    public List<ProjectMap> getModuleByProjectId(Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        wrapper.select("module_id", "module_name");
        return projectMapMapper.selectList(wrapper);
    }

    public int deleteApiByModuleId(Integer projectId, Integer moduleId, Integer apiId) {

        ProjectMap byProjectId = getByProjectId(projectId);

        QueryWrapper<ProjectMap> getModule = new QueryWrapper<>();
        getModule.eq("module_id", moduleId).eq("pid", byProjectId.getId());
        ProjectMap byModule = projectMapMapper.selectOne(getModule);

        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("api_id", apiId).eq("pid", byModule.getId());

        return projectMapMapper.delete(wrapper);
    }

    public int insert(ProjectMap projectMap) {
        return projectMapMapper.insert(projectMap);
    }

    public int delete(String operation, Integer moduleOrApi, Integer pid) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        String column;
        switch (operation) {
            case "module":
                column = "module_id";
                break;
            case "api":
                column = "api_id";
                break;
            default:
                column = "module_id";
        }
        wrapper.eq("pid", pid).eq(column, moduleOrApi);

        return projectMapMapper.delete(wrapper);
    }

    public ProjectMap getModuleByProjectIdAndModuleId(Integer moduleId, Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("module_id", moduleId).eq("pid", projectId);
        return projectMapMapper.selectOne(wrapper);
    }

    public ProjectMap getApiByModuleIdAndApiId(Integer projectId, Integer moduleId, Integer apiId) {

        ProjectMap byProjectId = getByProjectId(projectId);

        ProjectMap module = getModuleByProjectIdAndModuleId(moduleId, byProjectId.getId());

        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("api_id", apiId).eq("pid", module.getId());
        return projectMapMapper.selectOne(wrapper);
    }

    public int updateById(ProjectMap projectMap) {
        return projectMapMapper.updateById(projectMap);
    }

    public ProjectMap getByProjectId(Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId).isNull("pid");
        return projectMapMapper.selectOne(wrapper);
    }

    public void deleteWholdProject(Integer projectId) {

        List<Integer> ids = new ArrayList<>();

        ProjectMap byProjectId = getByProjectId(projectId);

        Integer pid = byProjectId.getId();

        ids.add(pid);

        QueryWrapper<ProjectMap> moduleWrapper = new QueryWrapper<>();
        moduleWrapper.select("id").eq("pid", pid);
        List<ProjectMap> moduleMapIds = projectMapMapper.selectList(moduleWrapper);

        QueryWrapper<ProjectMap> apiWrapper = new QueryWrapper<>();
        for (ProjectMap moduleMapId : moduleMapIds) {
            ids.add(moduleMapId.getId());
            apiWrapper.select("id").eq("pid", moduleMapId.getId());
            List<ProjectMap> apiMapIds = projectMapMapper.selectList(apiWrapper);
            for (ProjectMap apiMapId : apiMapIds) {
                ids.add(apiMapId.getId());
            }

        }

        projectMapMapper.deleteBatchIds(ids);

    }

    public ProjectMap getByModuleId(Integer moduleId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("module_id", moduleId);
        return projectMapMapper.selectOne(wrapper);
    }

}
