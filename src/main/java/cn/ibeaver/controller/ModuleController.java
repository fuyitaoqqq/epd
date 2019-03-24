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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName ModuleController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 11:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/project/{projectId}")
@Api(tags = "模块管理")
public class ModuleController {

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private IProjectService projectService;

	@ApiOperation(value = "添加模块", notes = "添加模块", httpMethod = "POST",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "模块名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "uri", value = "模块统一uri接口前缀，可为null", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "description", value = "模块描述", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "apiList", value = "模块下属api，格式为[/api,/api]", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "模块统一uri接口前缀，可为null", required = true, dataType = "number", paramType = "query")
	})
	@RequestMapping(value = "/module", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto addModule(@ApiIgnore Module module, @PathVariable("projectId") Integer projectId) {
		Boolean exist = ifProjectExist(projectId);
		if (exist && module.getProjectId().equals(projectId)) {
			int i = moduleService.addModule(module);
			if (i == 1) {
				return ResultDto.success();
			}
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	@ApiOperation(value = "删除模块", httpMethod = "DELETE",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteModule(@PathVariable("projectId") Integer projectId,
								  @PathVariable("moduleId") Integer moduleId) {

		Boolean exist = ifProjectExist(projectId);
		if (exist) {
			int i = moduleService.deleteModuleById(moduleId, projectId);
			if (i == 1) {
				return ResultDto.success();
			}
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());

	}

	@ApiOperation(value = "获取模块", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getModuleById(@PathVariable("projectId") Integer projectId,
								   @PathVariable("moduleId") Integer moduleId) {
		Boolean exist = ifProjectExist(projectId);
		if (exist) {
			Module module = moduleService.getModuleByIdAndProjectId(moduleId, projectId);
			if (module != null) {
				return ResultDto.success(module);
			}
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());

	}

	@ApiOperation(value = "更新模块", notes = "更新模块", httpMethod = "PUT",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "模块名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "uri", value = "模块统一uri接口前缀，可为null", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "description", value = "模块描述", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "apiList", value = "模块下属api，格式为[/api,/api]", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "模块统一uri接口前缀，可为null", required = true, dataType = "number", paramType = "query")
	})
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto updateModule(@ApiIgnore Module module,
								  @PathVariable("projectId") Integer projectId,
								  @PathVariable("moduleId") Integer moduleId) {
		Boolean exist = ifProjectExist(projectId);
		if (exist) {
			module.setId(moduleId);
			int i = moduleService.updateModule(module);
			if (i == 1) {
				return ResultDto.success();
			}
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	private Boolean ifProjectExist(Integer projectId) {
		Project project = projectService.getProjectById(projectId);
		if (project != null) {
			return true;
		}
		return false;
	}

}
