package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.cubesofttech.model.QRCodeList;


public interface QRCodeDAO {

	public List<QRCodeList> findAll() throws Exception;
	
	public QRCodeList findByQRId(int articleId) throws Exception;

	Integer getMaxId() throws Exception;
		
	void update(QRCodeList qrcode_list) throws Exception;
	
	void save(QRCodeList qrcode_list) throws Exception;
	
	void delete(QRCodeList qrcode_list) throws Exception;

}
