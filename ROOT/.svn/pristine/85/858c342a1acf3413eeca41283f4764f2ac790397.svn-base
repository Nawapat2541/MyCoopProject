package com.cubesofttech.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "article_tag")
@NamedQueries({ @NamedQuery(name = "ArticleTag.findAll", query = "SELECT t FROM ArticleTag t") })
public class ArticleTag implements Serializable{
	
	public ArticleTag() {
		
	}

	public ArticleTag (String articleId,
			String tagId
			) {
		this.articleId = articleId;
	}
	
	@Id
	@Column(name = "article_id")
	private String articleId;
	
	@Id
	@Column(name = "tag_id")
	private String tagId;
	
	public String getArticleId() {
		return articleId;
	}
	
	public void setArticleId(String articleId) {
		this.articleId= articleId;
	}
	
	public String getTagId() {
		return tagId;
	}
	
	public void setTagId(String tagId) {
		this.tagId= tagId;
	}
}
