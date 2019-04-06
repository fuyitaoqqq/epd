package cn.animalcity.apidocument.utils;

import cn.animalcity.apidocument.dto.ApiDto;
import cn.animalcity.apidocument.dto.ModuleDto;
import cn.animalcity.apidocument.dto.ProjectDto;
import cn.animalcity.apidocument.pojo.Api;
import cn.animalcity.apidocument.pojo.Module;
import cn.animalcity.apidocument.pojo.Project;
import com.google.common.collect.Lists;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

/**
 * 需考虑将浅拷贝换成深拷贝
 * @author: fuyitao
 * @date: 2019/04/05
 */

public class ConvertUtil {

    /**
     * 将ProjectDto转化为Project
     * @param dto
     * @return
     */
    public static Project convertDtoToProject(ProjectDto dto) {
        Project project = new Project();
        BeanCopier beanCopier = BeanCopier.create(ProjectDto.class, Project.class, false);
        beanCopier.copy(dto, project, null);
        project.setUri(dto.getShorthand());
        return project;
    }

    /**
     * 将projectList转化为dtoList
     * @param projectList
     * @return
     */
    public static List<ProjectDto> convertProjectListToProjectDtoList(List<Project> projectList) {
        List<ProjectDto> dtoList = Lists.newArrayList();
        BeanCopier beanCopier = BeanCopier.create(Project.class, ProjectDto.class, false);

        for (Project project : projectList) {
            ProjectDto dto = convertProjectToDto(project);
            dto.setShorthand(project.getUri());
            dto.setModuleList(Lists.newArrayList());
            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * 将Project转化为ProjectDto
     * @param project
     * @return
     */
    public static ProjectDto convertProjectToDto(Project project) {
        ProjectDto dto = new ProjectDto();
        BeanCopier beanCopier = BeanCopier.create(Project.class, ProjectDto.class, false);
        beanCopier.copy(project, dto, null);
        dto.setShorthand(project.getUri());
        dto.setModuleList(Lists.newArrayList());
        return dto;
    }

    /**
     * 将module转化为moduleDto
     * @param module module
     * @return ModuleDto
     */
    public static ModuleDto convertModuleToDto(Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setModuleId(module.getId());
        dto.setModuleName(module.getName());
        dto.setUri(module.getUri());
        dto.setApiList(Lists.newArrayList());
        return dto;
    }

    /**
     * 将moduleDto转化为module
     * @param moduleDto moduleDto
     * @return Module
     */
    public static Module convertModuleDtoToModule(ModuleDto moduleDto) {
        Module module = new Module();
        module.setId(moduleDto.getModuleId());
        module.setName(moduleDto.getModuleName());
        module.setUri(moduleDto.getUri());
        return module;
    }

    /**
     * 将apiDto转化为api
     * @param apiDto apidto
     * @return api
     */
    public static Api convertApiDtoToApi(ApiDto apiDto) {
        Api api = new Api();
        api.setId(apiDto.getApiId());
        api.setName(apiDto.getApiName());
        api.setUri(apiDto.getUri());
        api.setDescription(apiDto.getDescription());
        api.setContentType(apiDto.getContentType());
        api.setMethod(apiDto.getMethod());
        api.setRequestParam(apiDto.getRequestParam());
        api.setResponseParam(apiDto.getResponseParam());
        return api;
    }

    /**
     * 将api转化为apiDto
     * @param api api
     * @return apiDto
     */
    public static ApiDto convertApiToDto(Api api) {
        ApiDto apiDto = new ApiDto();
        BeanCopier beanCopier = BeanCopier.create(Api.class, ApiDto.class, false);
        beanCopier.copy(api, apiDto, null);
        apiDto.setApiId(api.getId());
        apiDto.setApiName(api.getName());
        return apiDto;
    }

}
