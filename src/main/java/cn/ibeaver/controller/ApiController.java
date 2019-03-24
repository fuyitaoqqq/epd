/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.controller;

import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.dto.ResultDto;
import cn.ibeaver.pojo.Api;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.service.IApiService;
import cn.ibeaver.service.IModuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @ClassName ApiController
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:48
 * @Version 1.0
 **/
@RestController
@RequestMapping("/project/{projectId}/module/{moduleId}")
@io.swagger.annotations.Api("API接口")
public class ApiController {

	@Autowired
	private IApiService apiService;

	@Autowired
	private IModuleService moduleService;

	@ApiOperation(value = "添加接口", notes = "添加接口，接口详情")
	@RequestMapping(value = "/api", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto addApi(Api api,
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

	@RequestMapping(value = "/api/{apiId}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDto updateApi(Api api,
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
	}

}
