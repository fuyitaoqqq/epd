/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.service.IModuleService;
import cn.ibeaver.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = "项目管理")
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IModuleService moduleService;

	@ApiOperation(value = "获取项目列表", httpMethod = "GET")
	@RequestMapping(value = "/project", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getIndex() {
		List<Project> projectList = projectService.getProjects();

		if (projectList.size() == 0) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		} else {
			return ResultDto.success(projectList);
		}
	}

	@ApiOperation(value = "获取项目详情", notes = "返回该项目所属的模块详情", httpMethod = "GET",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getProjectById(@PathVariable("id") Integer id) {
		Project project = projectService.getProjectById(id);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		} else {
			Map map = new HashMap(); //存放项目下的模块相关数据
			List<Module> moduleList = moduleService.getModulesByProjectId(id);
			map.put("project", project);
			map.put("moduleList", moduleList);
			return ResultDto.success(map);
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
	@RequestMapping(value = "/project", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto addProject(@ApiIgnore Project project) {
		int i = projectService.addProject(project);
		return ifSuccess(i);
	}

	@ApiOperation(value = "删除项目", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteProject(@PathVariable("id") Integer id) {
		int i = projectService.deleteProjectById(id);
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
			return ResultDto.fail(ResultContants.SYS_ERR.getCode(), ResultContants.SYS_ERR.getMsg());
		}
	}

}
