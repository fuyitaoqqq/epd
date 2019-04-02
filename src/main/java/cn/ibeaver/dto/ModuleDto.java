package cn.ibeaver.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author: welcome
 * @date: 2019/04/02
 */

@Data
@Accessors(chain = true)
public class ModuleDto {

    private Integer moduleId;
    private String moduleName;
    private String uri;
    private List<ApiMapDto> apiList;

}
