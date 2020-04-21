package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.cubesofttech.model.Article;
import com.cubesofttech.model.ArticleTag;

public interface ArticleTagDAO {

	int deleteByArticleId(String articleId) throws Exception;

	void save(ArticleTag articletag) throws Exception;

}
