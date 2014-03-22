package com.sinaapp.mydemo27.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class SuperDao {
	@Resource(name="sqlSession")
	public SqlSessionTemplate template;
}
