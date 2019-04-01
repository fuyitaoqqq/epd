/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.pojo.SysUser;
import cn.ibeaver.service.ModuleService;
import cn.ibeaver.service.ProjectService;
import cn.ibeaver.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

/**
 * @ClassName ModuleController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 11:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/project/{shorthand}")
@Api(tags = "模块管理")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ProjectService projectService;

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
	public ResultDto addModule(@ApiIgnore @RequestBody Module module,
							   @PathVariable("shorthand") String shorthand,
							   Principal principal) {

		SysUser user = CommonUtil.getUserByPrincipal(principal);

		Project project = ifProjectExist(shorthand);

		if (project != null) {
			moduleService.addModule(module, user, project.getId());
			return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), module);
		}

		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	@ApiOperation(value = "删除模块", httpMethod = "DELETE",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteModule(@PathVariable("shorthand") String shorthand,
								  @PathVariable("moduleId") Integer moduleId) {

		Project project = ifProjectExist(shorthand);
		if (project != null) {
			moduleService.deleteModule(moduleId, project.getId());
			return ResultDto.success();
		}

		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	@ApiOperation(value = "获取模块", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getModuleById(@PathVariable("shorthand") String shorthand,
								   @PathVariable("moduleId") Integer moduleId) {
		Project project = ifProjectExist(shorthand);
		if (project != null) {
			Module module = moduleService.getModuleById(moduleId);
			return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), module);
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
	public ResultDto updateModule(@ApiIgnore @RequestBody Module module,
								  @PathVariable("shorthand") String shorthand,
								  @PathVariable("moduleId") Integer moduleId,
								  Principal principal) {

		SysUser user = CommonUtil.getUserByPrincipal(principal);

		Project project = ifProjectExist(shorthand);
		if (project != null) {
			module.setId(moduleId);
			moduleService.updateModule(module, user, project.getId());
			Module moduleById = moduleService.getModuleById(moduleId);
			return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), moduleById);
		}

		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	private Project ifProjectExist(String shorthand) {
		Project project = projectService.getProjectByShorthand(shorthand);
		if (project == null) {
			return null;
		}
		return project;
	}

}
