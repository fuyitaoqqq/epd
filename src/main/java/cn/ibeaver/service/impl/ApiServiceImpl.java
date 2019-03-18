package cn.ibeaver.service.impl;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.dao.ApiMapper;
import cn.ibeaver.pojo.Api;
import cn.ibeaver.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName ApiServiceImpl
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:45
 * @Version 1.0
 **/
@Service("apiService")
public class ApiServiceImpl implements IApiService {

	@Autowired
	private ApiMapper apiMapper;

	@Override
	public int addApi(Api api) {
		api.setCreateTime(new Date());
		return apiMapper.addApi(api);
	}

	@Override
	public int deleteApi(Integer id, Integer moduleId, Integer projectId) {
		return apiMapper.deleteApi(id, moduleId, projectId);
	}

	@Override
	public int updateApi(Api api) {
		api.setUpdateTime(new Date());
		return apiMapper.updateApi(api);
	}

	@Override
	public Api getApiById(Integer id, Integer moduleId, Integer projectId) {
		return apiMapper.getApiById(id, moduleId, projectId);
	}
}
