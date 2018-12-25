package com.iba.storebackend.dao;

import java.util.List;

import com.iba.storebackend.dto.Product;

public interface ProductDAO {
	
	Product get(int id);
	List<Product> listProduct();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	List<Product> listActiveProduct();
	List<Product> listActiveProductByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);

}
