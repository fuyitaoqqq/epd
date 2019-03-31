/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import cn.ibeaver.dto.*;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.service.IModuleService;
import cn.ibeaver.service.ProjectMapService;
import cn.ibeaver.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 00:21
 * @Version 1.0
 **/
@RestController
@Api(tags = "项目管理")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectMapService projectMapService;

	@Autowired
	private IModuleService moduleService;

	@ApiOperation(value = "获取项目列表", httpMethod = "GET")
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ResultDto getIndex() {
		List<Project> projectList = projectService.getProjects();

		if (projectList.size() == 0) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		} else {
			List<ProjectDto> list = new ArrayList<>();
			for (Project project : projectList) {
				ProjectDto projectDto = new ProjectDto();
				BeanCopier beanCopier = BeanCopier.create(Project.class, ProjectDto.class, false);
				beanCopier.copy(project, projectDto, null);
				list.add(projectDto);
			}
			return ResultDto.success(list);
		}
	}

	@ApiOperation(value = "获取项目详情", notes = "返回该项目所属的模块详情", httpMethod = "GET",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/project/{shorthand}", method = RequestMethod.GET)
	public ResultDto getProjectByShorthand(@PathVariable("shorthand") String shorthand) {
		Project project = projectService.getProjectByShorthand(shorthand);

		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		} else {

			ProjectDto projectDto = new ProjectDto();
			BeanCopier beanCopier = BeanCopier.create(Project.class, ProjectDto.class, false);
			beanCopier.copy(project, projectDto, null);

			List<ProjectMap> moduleMapList = projectMapService.getModuleByProjectId(project.getId());
			List<ModuleMapDto> moduleListToProject = new ArrayList<>();

			Set<ProjectMap> moduleSet = new HashSet<>();
			for (ProjectMap moduleList : moduleMapList) {
				moduleSet.add(moduleList);
			}

			for (ProjectMap module : moduleSet) {
				ModuleMapDto moduleMapDto = new ModuleMapDto();
				moduleMapDto.setModuleId(module.getModuleId());
				moduleMapDto.setModuleName(module.getModuleName());

				List<ApiMapDto> apiListToProject = new ArrayList<>();
				List<ProjectMap> apiMapList = projectMapService.getApiByModuleId(module.getModuleId());
				for (ProjectMap apiMap : apiMapList) {
					ApiMapDto apiMapDto = new ApiMapDto();
					apiMapDto.setApiId(apiMap.getApiId());
					apiMapDto.setApiName(apiMap.getApiName());
					apiListToProject.add(apiMapDto);
				}

				moduleMapDto.setApiList(apiListToProject);
				moduleListToProject.add(moduleMapDto);

			}

			projectDto.setModuleList(moduleListToProject);

			return ResultDto.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), projectDto);
		}
	}

	@ApiOperation(value = "创建项目", httpMethod = "POST",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "项目名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "description", value = "项目描述", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestHeader", value = "全局参数：响应头", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestParam", value = "全局参数：请求参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestSuccess", value = "全局参数：成功返回参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestFail", value = "全局参数：失败返回参数", required = false, dataType = "string", paramType = "query")
	})
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ResultDto addProject(@ApiIgnore @RequestBody Project project) {
		project.setOwner(0);
		projectService.addProject(project);
		Project projectById = projectService.getProjectById(project.getId());

		return ResultDto.success(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), project);
	}

	@ApiOperation(value = "删除项目", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/project/{shorthand}", method = RequestMethod.DELETE)
	public ResultDto deleteProject(@PathVariable("shorthand") String shorthand) {
		int i = projectService.deleteProjectByShorthand(shorthand);
		return ifSuccess(i);
	}

	@ApiOperation(value = "更新项目信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "项目名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "description", value = "项目描述", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestHeader", value = "全局参数：响应头", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestParam", value = "全局参数：请求参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestSuccess", value = "全局参数：成功返回参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestFail", value = "全局参数：失败返回参数", required = false, dataType = "string", paramType = "query")
	})
	@RequestMapping(value = "/project/{shorthand}", method = RequestMethod.PUT)
	public ResultDto updateProject(@PathVariable("shorthand") String shorthand, @RequestBody Project project) {
		if (shorthand.equals(project.getShorthand())) {
			int i = projectService.updateProject(project);
			return ifSuccess(i);
		}
		return ResultDto.fail(ResultContants.SYS_ERR.getCode(), ResultContants.SYS_ERR.getMsg());
	}

	private ResultDto ifSuccess(Integer i) {
		if (i == 1) {
			return ResultDto.success();
		} else {
			return ResultDto.fail(ResultContants.SYS_ERR.getCode(), ResultContants.SYS_ERR.getMsg());
		}
	}

}
