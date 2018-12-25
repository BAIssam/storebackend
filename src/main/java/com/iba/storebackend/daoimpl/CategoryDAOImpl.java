package com.iba.storebackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iba.storebackend.dao.CategoryDAO;
import com.iba.storebackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> listActiveCategory() {
		
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		return sessionFactory.getCurrentSession().createQuery(selectActiveCategory, Category.class)
				.setParameter("active", true)
				.getResultList();
	}
	
	@Override
	public List<Category> listCategory() {
		String selectCategory = "FROM Category";

		return sessionFactory.getCurrentSession().createQuery(selectCategory, Category.class).getResultList();
	}

	@Override
	public Category get(int id) {
						
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override	
	public boolean add(Category category) {
		
		try{
			sessionFactory.getCurrentSession().persist(category);
			
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			//sessionFactory.getCurrentSession().clear();
			return false;
		}
		
	}

	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		
		try{
			sessionFactory.getCurrentSession().delete(category);
			
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean manageActivation(Category category) {
		
		category.setActive(false);
		
		try{
			sessionFactory.getCurrentSession().update(category);
			
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	

}
