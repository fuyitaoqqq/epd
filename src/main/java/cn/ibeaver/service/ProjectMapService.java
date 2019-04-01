package cn.ibeaver.service;

import cn.ibeaver.dao.ProjectMapMapper;
import cn.ibeaver.pojo.ProjectMap;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<ProjectMap> getApiByModuleId(Integer moduleId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("module_id", moduleId);
        wrapper.select("api_id", "api_name");
        return projectMapMapper.selectList(wrapper);
    }

    public int insert(ProjectMap projectMap) {
        return projectMapMapper.insert(projectMap);
    }

    public int delete(String operation, Integer moduleOrApi, Integer pid) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        switch (operation) {
            case "module":
                operation = "module_id";
                break;
            case "api":
                operation = "api_id";
                break;
            default:
                operation = "module_id";
        }
        wrapper.eq("pid", pid).eq(operation, moduleOrApi);

        return projectMapMapper.delete(wrapper);
    }

    public ProjectMap getModuleByProjectIdAndModuleId(Integer moduleId, Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("module_id", moduleId).eq("pid", projectId);
        return projectMapMapper.selectOne(wrapper);
    }

    public int updateById(ProjectMap projectMap) {
        return projectMapMapper.updateById(projectMap);
    }

    public ProjectMap getByProjectId(Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        return projectMapMapper.selectOne(wrapper);
    }

}
