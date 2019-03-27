/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.service;

import cn.ibeaver.dao.ProjectMapper;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.utils.CommonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public int addProject(Project project) {
		project.setShorthand(CommonUtil.generateUUID());
		project.setCreateTime(new Date());
		return projectMapper.addProject(project);
	}

	public int deleteProjectByShorthand(String shorthand) {
		return projectMapper.deleteProjectByShorthand(shorthand);
	}

	public int updateProject(Project project) {
		project.setUpdateTime(new Date());
		return projectMapper.updateProject(project);
	}

	public Project getProjectByShorthand(String shorthand) {
		return projectMapper.getProjectByShorthand(shorthand);
	}

	public List<Project> getProjects() {
		return projectMapper.getProjects();
	}

	public Project getProjectById(Integer id) {
		return projectMapper.getProjectById(id);
	}

}
