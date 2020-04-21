package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.cubesofttech.model.ArticleType;


public interface ArticleTypeDAO {

	public List<ArticleType> findAll() throws Exception;

	public ArticleType findByArticleTypeId(int articleTypeId) throws Exception;


}
