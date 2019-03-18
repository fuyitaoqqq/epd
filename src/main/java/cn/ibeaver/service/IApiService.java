/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.service;

import cn.ibeaver.pojo.Api;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ApiService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:43
 * @Version 1.0
 **/
public interface IApiService {

	int addApi(Api api);

	int deleteApi(@Param("id") Integer id, @Param("moduleId") Integer moduleId, @Param("projectId") Integer projectId);

	int updateApi(Api api);

	Api getApiById(@Param("id") Integer id, @Param("moduleId") Integer moduleId, @Param("projectId") Integer projectId);

}
