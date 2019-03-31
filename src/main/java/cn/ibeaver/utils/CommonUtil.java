package cn.ibeaver.utils;/**
 * Created by fuyitao on 19-3-22.
 */

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName CommonUtil
 * @Description TODO 通用工具类
 * @Author fuyitao
 * @Date 2019-3-22 17:25
 * @Version 1.0
 **/
public class CommonUtil {

	/**
	 * String转List，[aaa, bbb]
	 * @param string
	 * @return
	 */
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

	/**
	 * list转String，[aaa, bbb]
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {

		String join = StringUtils.join(list.toArray(), ",");
		return "[" + join + "]";

	}

	/**
	 * 汉字转化为拼音
	 * thanks com.belerweb.pinyin4j
	 * @param hanzi
	 * @return
	 */
	public static String getHanziPinYin(String hanzi) {
		String result = null;
		if(StringUtils.isNotBlank(hanzi)) {
			char[] charArray = hanzi.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (char ch : charArray) {
				// 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
				String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
				if(null != stringArray) {
					// 把第几声这个数字给去掉
					sb.append(stringArray[0].replaceAll("\\d", "")).append("-");
				}
			}
			if(sb.length() > 0) {
				result = sb.toString();
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
