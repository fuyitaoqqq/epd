package cn.ibeaver.service.impl;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.dao.ProjectMapper;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectServiceImpl
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-19 00:19
 * @Version 1.0
 **/
@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public int addProject(Project project) {
		project.setCreateTime(new Date());
		return projectMapper.addProject(project);
	}

	@Override
	public int deleteProjectById(Integer id) {
		return projectMapper.deleteProjectById(id);
	}

	@Override
	public int updateProject(Project project) {
		project.setUpdateTime(new Date());
		return projectMapper.updateProject(project);
	}

	@Override
	public Project getProjectById(Integer id) {
		return projectMapper.getProjectById(id);
	}

	@Override
	public List<Project> getProjects() {
		return projectMapper.getProjects();
	}
}
