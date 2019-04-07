package cn.animalcity.apidocument.service;

import cn.animalcity.apidocument.dao.ProjectMapper;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.pojo.Project;
import cn.animalcity.apidocument.pojo.ProjectMap;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.projectenum.ProjectMapEnum;
import cn.animalcity.apidocument.utils.CommonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Service
public class ProjectService {

    private final ProjectMapper projectMapper;

    private final ProjectMapService projectMapService;

    @Autowired
    public ProjectService(ProjectMapper projectMapper, ProjectMapService projectMapService) {
        this.projectMapper = projectMapper;
        this.projectMapService = projectMapService;
    }

    public List<Project> getProjects(Integer owner) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("owner", owner).or().eq("open", 1).orderByDesc("create_time");
        return projectMapper.selectList(wrapper);
    }

    public Project getProjectByUri(String uri) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("uri", uri);
        return projectMapper.selectOne(wrapper);
    }

    /**
     * 添加项目，如果两条数据均成功插入，返回插入的project对象
     *
     * @param project
     * @param user
     * @return
     */
    public Project addProject(Project project, SysUser user) {
        project.setUri(CommonUtil.generateUUID());
        project.setCreateTime(new Date());
        project.setOwner(user.getId());
        project.setOwnerName(user.getLoginName());
        int i = projectMapper.insert(project);

        int j = projectMapService.insert(project);

        if (i != 0 && j != 0) {
            return project;
        }
        return null;
    }

    /**
     * 删除项目时要将项目下的module api project_map全部删除
     *
     * @param uri uri
     * @return 成功返回0，失败返回-3
     */
    public int deleteProjectByShorthand(String uri) {
        Project project = getProjectByUri(uri);

        int i = projectMapService.deleteWholeProject(project.getId());
        int j = projectMapper.deleteById(project.getId());

        if (i == 0 && j == 0) {
            return ResultConstants.SYS_ERR.getCode();
        }

        return ResultConstants.SUCCESS.getCode();
    }

    /**
     * 更新project与projectMap
     * @param project
     * @return
     */
    public Project updateProject(Project project, String uri) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("uri", uri);
        int i = projectMapper.update(project, wrapper);

        ProjectMap projectMap = new ProjectMap();
        projectMap.setName(project.getName());
        projectMap.setUri(project.getUri());
        int j = projectMapService.updateProjectMap(projectMap, ProjectMapEnum.PROJECT, uri);

        if (i == 0 && j == 0) {
            return null;
        }

        return getProjectByUri(uri);
    }

    public String getBaseUrl(Integer projectId) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.select("base_url").eq("id", projectId);
        return projectMapper.selectOne(wrapper).getBaseUrl();
    }
}
