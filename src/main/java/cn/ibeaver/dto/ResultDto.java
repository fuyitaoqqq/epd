/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @ClassName ResultDto
 * @Description TODO 返回DTO
 * @Author fuyitao
 * @Date 2019-3-18 00:23
 * @Version 1.0
 **/
@Data
@ApiModel(description = "返回实体类")
public class ResultDto {

	@ApiModelProperty(value = "状态码", notes = "后台相应成功，code为0，响应错误，code为负数")
	private Integer code;

	@ApiModelProperty(value = "响应信息")
	private String msg;

	@ApiModelProperty(value = "返回数据")
	private Object data;

	private ResultDto(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResultDto success(Object data) {
		return new ResultDto(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), data);
	}

	public static ResultDto success() {
		return new ResultDto(ResultContants.SUCCESS.getCode(), ResultContants.SUCCESS.getMsg(), null);
	}

	public static ResultDto fail(Integer code, String msg) {
		return new ResultDto(code, msg, null);
	}
}
