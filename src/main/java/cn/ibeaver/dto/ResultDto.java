/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.dto;

import lombok.Data;

/**
 * @ClassName ResultDto
 * @Description TODO 返回DTO
 * @Author fuyitao
 * @Date 2019-3-18 00:23
 * @Version 1.0
 **/
@Data
public class ResultDto {

	private Integer code;
	private String msg;
	private Object data;

	private ResultDto(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResultDto success(Object data) {
		return new ResultDto(ResultContants.SUCCESS, ResultContants.SUCCESS_MSG, data);
	}

	public static ResultDto success() {
		return new ResultDto(ResultContants.SUCCESS, ResultContants.SUCCESS_MSG, null);
	}

	public static ResultDto fail(Integer code, String msg) {
		return new ResultDto(code, msg, null);
	}
}
