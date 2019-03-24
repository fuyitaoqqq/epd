/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Api
 * @Description TODO 接口相关实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:54
 * @Version 1.0
 **/
@Data
public class Api {

	/* id */
	private Integer id;

	/* 接口URI */
	private String uri;

	/* 接口描述 */
	private String description;

	/* 请求方式 */
	private String method;

	/* 接口中的参数，请求头 */
	private String requestHeader;

	/* 接口中的参数，请求参数 */
	private String requestParam;

	/* 接口中的参数，成功返回参数 */
	private String requestSuccess;

	/* 接口中的参数，失败返回参数 */
	private String requestFail;

	/* 返回成功json */
	private String returnSuccessJson;

	/* 返回失败json */
	private String returnFailJson;

	/* 所属项目ID */
	private Integer projectId;

	/* 所属模块ID */
	private Integer moduleId;

	/* 创建时间 */
	private Date createTime;

	/* 修改时间 */
	private Date updateTime;
}
