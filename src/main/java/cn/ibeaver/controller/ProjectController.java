/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.service.IProjectService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 00:21
 * @Version 1.0
 **/
@RestController
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@RequestMapping(value = "/project", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getIndex() {
		List<Project> projectList = projectService.getProjects();

		if (projectList.size() == 0) {
			return ResultDto.fail(ResultContants.DATA_BLANK, ResultContants.DATA_BLANK_MSG);
		} else {
			return ResultDto.success(projectList);
		}
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getProjectById(@PathVariable("id") Integer id) {
		Project project = projectService.getProjectById(id);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK, ResultContants.DATA_BLANK_MSG);
		} else {
			Map map = new HashMap(); //存放项目下的模块相关数据
			return ResultDto.success(project);
		}
	}

	@RequestMapping(value = "/project", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto addProject(Project project) {
		int i = projectService.addProject(project);
		return ifSuccess(i);
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteProject(@PathVariable("id") Integer id) {
		int i = projectService.deleteProjectById(id);
		return ifSuccess(i);
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto updateProject(Project project) {
		int i = projectService.updateProject(project);
		return ifSuccess(i);
	}

	private ResultDto ifSuccess(Integer i) {
		if (i == 1) {
			return ResultDto.success();
		} else {
			return ResultDto.fail(ResultContants.SYS_ERR, ResultContants.SYS_ERR_MSG);
		}
	}

}
