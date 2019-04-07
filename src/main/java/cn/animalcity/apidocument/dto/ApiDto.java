package cn.animalcity.apidocument.dto;

import lombok.Data;

/**
 * @author: fuyitao
 * @date: 2019/04/06
 */

@Data
public class ApiDto {

    /**
     * id
     */
    private Integer apiId;

    /**
     * 接口名
     */
    private String apiName;

    /**
     * 接口描述
     */
    private String description;

    /**
     * uri
     */
    private String uri;

    /**
     * 拼接给前台显示的uri
     * baseUrl/uri/uri
     */
    private String showUri;

    /**
     * content-type
     */
    private String contentType;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 相应参数
     */
    private String responseParam;

}
