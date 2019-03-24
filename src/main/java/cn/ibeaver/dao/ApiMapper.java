/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.dao;

import cn.ibeaver.pojo.Api;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApiMapper
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:10
 * @Version 1.0
 **/
@Component
public interface ApiMapper{

	int addApi(Api api);

	int deleteApi(@Param("id") Integer id, @Param("moduleId") Integer moduleId, @Param("projectId") Integer projectId);

	int updateApi(Api api);

	Api getApiById(@Param("id") Integer id, @Param("moduleId") Integer moduleId, @Param("projectId") Integer projectId);

}
