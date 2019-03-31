package cn.ibeaver.dto;

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
    private List<ApiMapDto> apiList;

}
