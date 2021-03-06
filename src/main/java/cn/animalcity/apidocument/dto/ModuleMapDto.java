package cn.animalcity.apidocument.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: welcome
 * @date: 2019/03/31
 */

@Data
public class ModuleMapDto {

    private Integer moduleId;
    private String moduleName;
    private String uri;
    private List<ApiMapDto> apiList;

}
