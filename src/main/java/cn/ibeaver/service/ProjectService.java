/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.service;

import cn.ibeaver.dao.ProjectMapper;
import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.pojo.SysUser;
import cn.ibeaver.utils.CommonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName IProjectService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 00:18
 * @Version 1.0
 **/
@Service("projectService")
public class ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectMapService projectMapService;

	@Autowired
	private ModuleService moduleService;

	public int addProject(Project project, SysUser user) {
		project.setShorthand(CommonUtil.generateUUID());
		project.setCreateTime(new Date());
		project.setOwner(user.getId());
		project.setOwnerName(user.getLoginName());
		int i = projectMapper.insert(project);

		ProjectMap projectMap = new ProjectMap();
		projectMap.setName(project.getName());
		projectMap.setProjectId(project.getId());

		int j = projectMapService.insert(projectMap);

		if (i ==0 && j == 0) {
			return ResultContants.SYS_ERR.getCode();
		}

		return ResultContants.SUCCESS.getCode();
	}

	@Transactional
	public int deleteProjectByShorthand(String shorthand) {

		Project project = getProjectByShorthand(shorthand);

		projectMapService.deleteWholdProject(project.getId());

		projectMapper.deleteProjectByShorthand(shorthand);

		System.out.println("the whold project delete successfully");

		return 0;
	}

	public int updateProject(Project project) {
		project.setUpdateTime(new Date());
		QueryWrapper<Project> wrapper = new QueryWrapper<>();
		wrapper.eq("shorthand", project.getShorthand());
		int i = projectMapper.update(project, wrapper);

		Project projectByShorthand = getProjectByShorthand(project.getShorthand());

		ProjectMap projectMap = projectMapService.getByProjectId(projectByShorthand.getId());
		projectMap.setName(project.getName());
		int j = projectMapService.updateById(projectMap);

		if (i == 0 && j == 0) {
			return ResultContants.SYS_ERR.getCode();
		}

		return ResultContants.SUCCESS.getCode();
	}

	public Project getProjectByShorthand(String shorthand) {
		QueryWrapper<Project> wrapper = new QueryWrapper<>();
		wrapper.eq("shorthand", shorthand);
		return projectMapper.selectOne(wrapper);
	}

	public List<Project> getProjects(Integer owner) {
		QueryWrapper<Project> wrapper = new QueryWrapper<>();
		wrapper.eq("owner", owner).or().eq("open", 1).orderByDesc("create_time");
		return projectMapper.selectList(wrapper);
	}

	public Project getProjectById(Integer id) {
		QueryWrapper<Project> wrapper = new QueryWrapper<>();
		wrapper.eq("id", id);

		return projectMapper.selectOne(wrapper);
	}

}
