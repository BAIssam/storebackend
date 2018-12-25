package com.iba.storebackend.dao;

import java.util.List;

import com.iba.storebackend.dto.Category;


public interface CategoryDAO {
	
	public Category get(int id);
	public List<Category> listActiveCategory();
	public List<Category> listCategory();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	boolean manageActivation(Category category);

}
