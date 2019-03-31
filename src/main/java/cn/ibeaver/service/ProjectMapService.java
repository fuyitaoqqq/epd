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

@Service("projectMapService")
public class ProjectMapService {

    @Autowired
    private ProjectMapMapper projectMapMapper;

    public List<ProjectMap> getProjectMapByProjectId(Integer projectId) {
        QueryWrapper<ProjectMap> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
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

}
