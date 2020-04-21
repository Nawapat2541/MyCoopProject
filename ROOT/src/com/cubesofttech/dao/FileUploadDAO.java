package com.cubesofttech.dao;

import java.util.List;

import com.cubesofttech.model.FileUpload;


public interface FileUploadDAO {
    
    public void save(FileUpload fileupload) throws Exception;
    
    public List<FileUpload> findAll() throws Exception;
    
    public FileUpload findById(String id) throws Exception;
    
    public void update(FileUpload fileupload) throws Exception;
    
    public void delete(FileUpload fileupload) throws Exception;
    
    Integer getMaxId() throws Exception;

	List<FileUpload> findByuser(String user) throws Exception;
}
