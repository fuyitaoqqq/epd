package cn.ibeaver.utils;/**
 * Created by fuyitao on 19-3-22.
 */

import cn.ibeaver.pojo.Module;
import cn.ibeaver.service.IModuleService;
import cn.ibeaver.service.impl.ModuleServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CommonUtil
 * @Description TODO 通用工具类
 * @Author fuyitao
 * @Date 2019-3-22 17:25
 * @Version 1.0
 **/
public class CommonUtil {

	public static List<String> stringToList(String string) {

		if (StringUtils.isNotBlank(string)) {
			String substring = string.substring(0, string.length()-1);
			List<String> list = Arrays.asList(StringUtils.split(substring, ","));
			return new ArrayList<>(list);
		} else {
			List<String> list = new ArrayList<>();
			return list ;
		}

	}

	public static String listToString(List<String> list) {

		String join = StringUtils.join(list.toArray(), ",");
		return "[" + join + "]";

	}

}
