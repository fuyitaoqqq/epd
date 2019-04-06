package cn.animalcity.apidocument.controller;

import cn.animalcity.apidocument.dto.*;
import cn.animalcity.apidocument.pojo.Project;
import cn.animalcity.apidocument.pojo.ProjectMap;
import cn.animalcity.apidocument.pojo.SysUser;
import cn.animalcity.apidocument.service.ProjectMapService;
import cn.animalcity.apidocument.service.ProjectService;
import cn.animalcity.apidocument.service.SysUserService;
import cn.animalcity.apidocument.utils.CommonUtil;
import cn.animalcity.apidocument.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@RestController
public class ProjectController {

    private final ProjectService projectService;

    private final ProjectMapService projectMapService;

    private final SysUserService sysUserService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMapService projectMapService, SysUserService sysUserService) {
        this.projectService = projectService;
        this.projectMapService = projectMapService;
        this.sysUserService = sysUserService;
    }

    /**
     * 获取项目列表
     *
     * @param principal 身份认证
     * @return projectDto list
     */
    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto getIndex(Principal principal) {
        SysUser user = CommonUtil.getUserByPrincipal(principal);

        List<Project> projectList = projectService.getProjects(user.getId());

        if (projectList.size() == 0) {
            return ResultDto.fail(ResultConstants.DATA_BLANK.getCode(), ResultConstants.DATA_BLANK.getMsg());
        } else {
            List<ProjectDto> dtoList = ConvertUtil.convertProjectListToProjectDtoList(projectList);
            return ResultDto.success(dtoList);
        }
    }

    /**
     * 获取项目详情
     *
     * @param uri 项目uri，是uuid，可用于定位project
     * @return
     */
    @GetMapping(value = "/project/{uri}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto getProjectByShorthand(@PathVariable("uri") String uri) {
        Project project = projectService.getProjectByUri(uri);

        if (project == null) {
            return ResultDto.fail(ResultConstants.DATA_BLANK.getCode(), ResultConstants.DATA_BLANK.getMsg());
        } else {

            ProjectDto projectDto = ConvertUtil.convertProjectToDto(project);

            List<ProjectMap> modules = projectMapService.getListByPid(project);
            List<ModuleMapDto> moduleList = new ArrayList<>();
            for (ProjectMap module : modules) {
                ModuleMapDto moduleMapDto = new ModuleMapDto();
                moduleMapDto.setModuleId(module.getModuleId());
                moduleMapDto.setModuleName(module.getName());
                moduleMapDto.setUri(module.getUri());

                List<ProjectMap> apis = projectMapService.getListByPid(module.getId());
                List<ApiMapDto> apiList = new ArrayList<>();
                for (ProjectMap api : apis) {
                    ApiMapDto apiMapDto = new ApiMapDto();
                    apiMapDto.setApiId(api.getApiId());
                    apiMapDto.setApiName(api.getName());
                    apiMapDto.setUri(api.getUri());
                    apiList.add(apiMapDto);
                }
                moduleMapDto.setApiList(apiList);
                moduleList.add(moduleMapDto);
            }

            projectDto.setModuleList(moduleList);

            return ResultDto.success(ResultConstants.SUCCESS.getCode(), ResultConstants.SUCCESS.getMsg(), projectDto);
        }
    }

    /**
     * 新增项目，成功时将project返回给前台
     *
     * @param projectDto
     * @param principal
     * @return
     */
    @PostMapping(value = "/project", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto addProject(@ApiIgnore @RequestBody ProjectDto projectDto, Principal principal) {
        SysUser user = sysUserService.getUserByLoginName(principal.getName());
        Project project = ConvertUtil.convertDtoToProject(projectDto);

        Project addProject = projectService.addProject(project, user);

        if (addProject != null) {
            return ResultDto.success(ConvertUtil.convertProjectToDto(addProject));
        }

        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

    @DeleteMapping(value = "/project/{uri}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto deleteProject(@PathVariable("uri") String uri) {
        int i = projectService.deleteProjectByShorthand(uri);

        if (i == 0) {
            return ResultDto.success();
        } else {
            return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
        }
    }

    @PutMapping(value = "/project/{uri}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto updateProject(@PathVariable("uri") String uri, @RequestBody ProjectDto projectDto) {

        Project project = ConvertUtil.convertDtoToProject(projectDto);

        Project newProject = projectService.updateProject(project, uri);

        if (newProject != null) {
            ProjectDto newDto = ConvertUtil.convertProjectToDto(newProject);
            return ResultDto.success(ResultConstants.SUCCESS.getCode(), ResultConstants.SUCCESS.getMsg(), newDto);
        }

        return ResultDto.fail(ResultConstants.SYS_ERR.getCode(), ResultConstants.SYS_ERR.getMsg());
    }

}
