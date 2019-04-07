package cn.animalcity.apidocument.controller;

import cn.animalcity.apidocument.dto.ApiDto;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.dto.ResultDto;
import cn.animalcity.apidocument.pojo.Api;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.service.ApiService;
import cn.animalcity.apidocument.service.ProjectService;
import cn.animalcity.apidocument.utils.CommonUtil;
import cn.animalcity.apidocument.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@RestController
@RequestMapping(value = "/project/{uri}/module/{moduleId}")
public class ApiController {

    private final ApiService apiService;

    private final ProjectService projectService;

    @Autowired
    public ApiController(ApiService apiService, ProjectService projectService) {
        this.apiService = apiService;
        this.projectService = projectService;
    }

    /**
     * 新增api
     * @param uri 项目uri标识
     * @param moduleId module id
     * @param apiDto 与前台直接交互的api dto
     * @param principal 用户认证
     * @return ApiDto
     */
    @PostMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto addApi(@PathVariable("uri") String uri,
                            @PathVariable("moduleId") Integer moduleId,
                            @RequestBody ApiDto apiDto,
                            Principal principal) {
        SysUser user = CommonUtil.getUserByPrincipal(principal);

        Api api = ConvertUtil.convertApiDtoToApi(apiDto);
        Integer projectId = projectService.getProjectByUri(uri).getId();
        api.setProjectId(projectId).setModuleId(moduleId);

        Api newApi = apiService.addApi(api, user);

        if (newApi != null) {
            ApiDto newDto = ConvertUtil.convertApiToDto(newApi);
            return ResultDto.success(newDto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    /**
     * 删除api
     * @param apiId apiId
     * @return 成功返回0，失败返回-3
     */
    @DeleteMapping(value = "/api/{apiId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto deleteApi(@PathVariable("apiId") Integer apiId) {
        int i = apiService.deleteApi(apiId);
        if (i == ResultConstants.SUCCESS.getCode()) {
            return ResultDto.success();
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    /**
     * 更新api信息
     * @param apiId apiId
     * @param apiDto 与前台交互的apiDto
     * @param principal 用户认证
     * @return ApiDto
     */
    @PutMapping(value = "/api/{apiId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto updateApi(@PathVariable("apiId") Integer apiId,
                               @RequestBody ApiDto apiDto,
                               Principal principal) {

        SysUser user = CommonUtil.getUserByPrincipal(principal);
        Api api = ConvertUtil.convertApiDtoToApi(apiDto);
        api.setId(apiId);
        Api newApi = apiService.updateApi(api, user);
        if (newApi != null) {
            ApiDto newDto = ConvertUtil.convertApiToDto(newApi);
            return ResultDto.success(newDto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    /**
     * 获取api详情
     * @param apiId apiID
     * @return
     */
    @GetMapping(value = "/api/{apiId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto getApi(@PathVariable("apiId") Integer apiId) {
        Api api = apiService.getApi(apiId);
        if (api != null) {
            ApiDto dto = ConvertUtil.convertApiToDto(api);
            return ResultDto.success(dto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());

    }

}
