package cn.animalcity.apidocument.controller;

import cn.animalcity.apidocument.dto.ModuleDto;
import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.dto.ResultDto;
import cn.animalcity.apidocument.pojo.Module;
import cn.animalcity.apidocument.pojo.Project;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.service.ModuleService;
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
@RequestMapping("/project/{uri}")
@RestController
public class ModuleController {

    private final ModuleService moduleService;

    private final ProjectService projectService;

    @Autowired
    public ModuleController(ModuleService moduleService, ProjectService projectService){
        this.moduleService = moduleService;
        this.projectService = projectService;
    }

    /**
     * 新增module
     * @param uri 根据uri判断module所属
     * @param moduleDto 接收到前台传回的moduleDto
     * @param principal 用户认证
     * @return moduleDto
     */
    @PostMapping(value = "/module", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto addModule(@PathVariable("uri") String uri,
                               @RequestBody ModuleDto moduleDto,
                               Principal principal) {
        SysUser user = CommonUtil.getUserByPrincipal(principal);
        Project project = projectService.getProjectByUri(uri);
        Module module = ConvertUtil.convertModuleDtoToModule(moduleDto);
        module.setProjectId(project.getId());
        Module newModule = moduleService.addModule(module, user);
        if (newModule != null) {
            ModuleDto newDto = ConvertUtil.convertModuleToDto(newModule);
            return ResultDto.success(newDto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    /**
     * 删除module
     * @param moduleId moduleId
     * @return 成功code = 0，失败code = -3
     */
    @DeleteMapping(value = "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto deleteModule(@PathVariable("moduleId") Integer moduleId) {
        int i = moduleService.deleteModule(moduleId);
        if (i == ResultConstants.SUCCESS.getCode()) {
            return ResultDto.success();
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    @PutMapping(value = "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto updateModule(@PathVariable("moduleId") Integer moduleId,
                                  @RequestBody ModuleDto moduleDto,
                                  Principal principal) {
        SysUser user = CommonUtil.getUserByPrincipal(principal);
        Module module = ConvertUtil.convertModuleDtoToModule(moduleDto);
        module.setId(moduleId);
        Module newModule = moduleService.updateModule(module, user);

        if (newModule != null) {
            ModuleDto newDto = ConvertUtil.convertModuleToDto(newModule);
            return ResultDto.success(newDto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    @GetMapping(value = "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto getModule(@PathVariable("moduleId") Integer moduleId) {
        Module module = moduleService.getModule(moduleId);
        if (module != null) {
            ModuleDto newDto = ConvertUtil.convertModuleToDto(module);
            return ResultDto.success(newDto);
        }
        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

}
