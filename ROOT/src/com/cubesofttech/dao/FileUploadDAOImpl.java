package com.cubesofttech.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*import com.cubesofttech.model.ExpenseGroup;*/
import com.cubesofttech.model.FileUpload;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
 
    @Override
    public void save(FileUpload FileUpload) throws Exception{
        Session session = this.sessionFactory.getCurrentSession();
        session.save(FileUpload);
        session.flush();
        //session.close();
    }

    @Override
    public List<FileUpload> findAll() throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        List<FileUpload> fileList = null;
        try {
        	fileList = session.createCriteria(FileUpload.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return fileList;
    }
    @Override
    public List<FileUpload> findByuser(String user) throws Exception {
    	Session session = this.sessionFactory.getCurrentSession();
		 List<FileUpload> holidayList = null;
		  try {
			 
		   String sql = "SELECT  * FROM file where user_create='"+user+"'  ";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.addEntity(FileUpload.class);
			  
		   holidayList = query.list();
		 	
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		   // session.close();
		  }
		  return holidayList;    
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Integer maxId = 0;

		try {

			Criteria criteria = session.createCriteria(FileUpload.class).setProjection(Projections.max("fileId"));
			maxId = (Integer) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			maxId = new Integer(0);

		} finally {

		}
		if (maxId != null) {
			return maxId;
		} else{
			return new Integer(0);
		}
	}
	
    @Override
    public FileUpload findById(String id) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        FileUpload FileUpload = null;
        try {
            FileUpload = (FileUpload) session.get(FileUpload.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return FileUpload;
    }

    @Override
    public void update(FileUpload FileUpload) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.clear();
        session.update(FileUpload);
        session.flush();
        //session.close();
    }

    @Override
    public void delete(FileUpload FileUpload) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(FileUpload);
        session.flush();
        //session.close();
    }
    
}
