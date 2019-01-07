package com.tecsun.sisp.adapter.modules.family.service.impl;


import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.family.entity.request.MyFamilyBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wunuanchan
 * @version 创建时间：2017年6月2日 下午3:57:01 说明：
 */
@Service("MyFamilyServiceImpl")
public class MyFamilyServiceImpl extends BaseService {

	public static final Logger logger = Logger
			.getLogger(MyFamilyServiceImpl.class);

	private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.family.service.impl.MyFamilyServiceImpl.";

	/* 获取家庭成员列表 */
	public List<MyFamilyBean> getFamilyList4cssp(String sfzh) {
		List<MyFamilyBean> MyFamilyBean = getSqlSessionTemplate().selectList(NAMESPACE + "getFamilyList", sfzh);
		return MyFamilyBean;
	}

	/* 获取家庭成员 */
	public MyFamilyBean getFamilyPersonInfo4cssp(MyFamilyBean bean) {
		MyFamilyBean MyFamilyBean = getSqlSessionTemplate().selectOne(NAMESPACE + "getFamilyPersonInfo", bean);
		return MyFamilyBean;
	}

	/* 添加家庭成员 */
	public int addFamily4cssp(MyFamilyBean bean) {
		int count = getSqlSessionTemplate().insert(NAMESPACE + "addFamily", bean);
		return count;
	}

	/* 删除家庭成员 */
	public int deleteFamily4cssp(MyFamilyBean bean) {
		int count = getSqlSessionTemplate().delete(NAMESPACE + "deleteFamily", bean);
		return count;
	}

	/* 修改家庭成员信息 */
	public int updateFamilyById4cssp(MyFamilyBean bean) {
		int count = getSqlSessionTemplate().update(NAMESPACE + "updateFamilyById", bean);
		return count;
	}

	/* 修改家庭成员状态 */
	public int updateStatusById4cssp(MyFamilyBean bean) {
		int count = getSqlSessionTemplate().update(NAMESPACE + "updateStatusById", bean);
		return count;
	}

}
