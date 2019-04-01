/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ApiController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:48
 * @Version 1.0
 **/
@RestController
@RequestMapping("/project/{projectId}/module/{moduleId}")
@io.swagger.annotations.Api(tags = "接口管理")
public class ApiController {

	/*@Autowired
	private IApiService apiService;

	@Autowired
	private IModuleService moduleService;

	@ApiOperation(value = "添加接口详情", notes = "添加接口，接口详情", httpMethod = "POST",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uri", value = "api接口地址", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "description", value = "api描述", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "method", value = "api请求方式", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "requestHeader", value = "请求头", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "requestParam", value = "请求参数", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "requestSuccess", value = "成功返回参数", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "requestFail", value = "失败返回参数", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "returnSuccessJson", value = "成功返回json示例", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "returnFailJson", value = "失败返回json实例", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "moduleId", value = "module ID", required = true, dataType = "integer", paramType = "query"),
		@ApiImplicitParam(name = "projectId", value = "project ID", required = true, dataType = "integer", paramType = "query")
	})
	@RequestMapping(value = "/api", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto addApi(@ApiIgnore Api api,
							@PathVariable("moduleId") Integer moduleId,
							@PathVariable("projectId") Integer projectId) {
		Boolean judgeParam = judgeParam(api, moduleId, projectId);
		Boolean moduleExist = ifModuleExist(moduleId, projectId);
		if (judgeParam && moduleExist) {
			int i = apiService.addApi(api);
			if (i == ResultContants.SUCCESS.getCode()) {
				return ResultDto.success();
			} else {
				return ResultDto.fail(ResultContants.SYS_ERR.getCode(), ResultContants.SYS_ERR.getMsg());
			}
		} else {
			return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
		}

	}

	@ApiOperation(value = "删除api接口详情", notes = "删除api接口详情", httpMethod = "DELETE",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/api/{apiId}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto deleteApi(@PathVariable("apiId") Integer apiId,
							   @PathVariable("moduleId") Integer moduleId,
							   @PathVariable("projectId") Integer projectId) {
		int i = apiService.deleteApi(apiId, moduleId, projectId);
		if (i == ResultContants.SUCCESS.getCode()) {
			return ResultDto.success();
		} else if (i == ResultContants.SYS_ERR.getCode()) {
			return ResultDto.fail(ResultContants.SYS_ERR.getCode(), ResultContants.SYS_ERR.getMsg());
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}

	@ApiOperation(value = "更新接口详情", notes = "更新接口详情", httpMethod = "PUT",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uri", value = "api接口地址", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "description", value = "api描述", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "method", value = "api请求方式", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestHeader", value = "请求头", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestParam", value = "请求参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestSuccess", value = "成功返回参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "requestFail", value = "失败返回参数", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "returnSuccessJson", value = "成功返回json示例", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "returnFailJson", value = "失败返回json实例", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "moduleId", value = "module ID", required = true, dataType = "integer", paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "project ID", required = true, dataType = "integer", paramType = "query")
	})
	@RequestMapping(value = "/api/{apiId}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto updateApi(@ApiIgnore Api api,
							   @PathVariable("apiId") Integer apiId,
							   @PathVariable("moduleId") Integer moduleId,
							   @PathVariable("projectId") Integer projectId) {
		Boolean judgeParam = judgeParam(api, moduleId, projectId);
		Api getOne = apiService.getApiById(apiId, moduleId, projectId);
		if (judgeParam && getOne != null) {
			api.setId(apiId);
			int i = apiService.updateApi(api);
			if (i == ResultContants.SUCCESS.getCode()) {
				return ResultDto.success();
			}
		}
		return ResultDto.fail(ResultContants.PARAM_ERR.getCode(), ResultContants.PARAM_ERR.getMsg());
	}
	@ApiOperation(value = "查询接口详情", notes = "查询接口详情", httpMethod = "GET",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/api/{apiId}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto getApi(@PathVariable("apiId") Integer apiId,
							@PathVariable("moduleId") Integer moduleId,
							@PathVariable("projectId") Integer projectId) {
		Api api = apiService.getApiById(apiId, moduleId, projectId);
		if (api != null) {
			return ResultDto.success(api);
		}
		return ResultDto.fail(ResultContants.DATA_BLANK.getCode(), ResultContants.DATA_BLANK.getMsg());
	}

	private Boolean ifModuleExist(Integer moduleId, Integer projectId) {
		Module module = moduleService.getModuleByIdAndProjectId(moduleId, projectId);
		if (module != null) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean judgeParam(Api api, Integer moduleId, Integer projectId) {
		return (api.getProjectId().equals(projectId) && api.getModuleId().equals(moduleId));
	}*/

}
