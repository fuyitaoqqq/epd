/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.dto;

/**
 * @ClassName ResultContants
 * @Description TODO 定义返回值常量
 * @Author fuyitao
 * @Date 2019-3-18 00:31
 * @Version 1.0
 **/
public enum ResultContants {

	/* 成功返回 */
	SUCCESS(0, "SUCCESS!"),

	/* 参数错误 */
	PARAM_ERR(-1, "参数错误！"),

	/* 没有数据 */
	DATA_BLANK(-2,"没有数据！"),

	/* 系统错误 */
	SYS_ERR(-3,"系统错误！"),

	BAD_CREDENTIAL(-6, "Authentication failed");

	private Integer code;
	private String msg;

	ResultContants(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode(){
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
