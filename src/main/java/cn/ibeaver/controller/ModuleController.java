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
import org.apache.commons.lang3.StringUtils;
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

		Project project = projectService.getProjectByShorthand(shorthand);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		}

		moduleService.addModule(module, user, project.getId());

		return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), module);
	}

	@ApiOperation(value = "删除模块", httpMethod = "DELETE",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteModule(@PathVariable("shorthand") String shorthand,
								  @PathVariable("moduleId") Integer moduleId) {

		Project project = projectService.getProjectByShorthand(shorthand);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		}

		moduleService.deleteModule(moduleId, project.getId());
		return ResultDto.success();
	}

	@ApiOperation(value = "获取模块", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getModuleById(@PathVariable("shorthand") String shorthand,
								   @PathVariable("moduleId") Integer moduleId) {
		Project project = projectService.getProjectByShorthand(shorthand);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		}

		Module module = moduleService.getModuleById(moduleId);
		return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), module);
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
								  Principal principal) {

		SysUser user = CommonUtil.getUserByPrincipal(principal);

		Project project = projectService.getProjectByShorthand(shorthand);
		if (project == null) {
			return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
		}

		moduleService.updateModule(module, user);
		return ResultDto.success(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), module);
	}

	/*private Boolean ifProjectExist(Integer projectId) {
//		Project project = projectService.getProjectById(projectId);
//		if (project != null) {
//			return true;
//		}
		return false;
	}*/

}
