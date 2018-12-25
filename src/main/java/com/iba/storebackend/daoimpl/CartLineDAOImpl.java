package com.iba.storebackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iba.storebackend.dao.CartLineDAO;
import com.iba.storebackend.dto.Cart;
import com.iba.storebackend.dto.CartLine;
import com.iba.storebackend.dto.OrderDetail;
import com.iba.storebackend.dto.Product;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	
	@Autowired
	SessionFactory sessionFactory; 

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String selectCartLine ="FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		
		try{
			return sessionFactory.getCurrentSession().createQuery(selectCartLine, CartLine.class)
					.setParameter("cartId", cartId)
					.setParameter("productId", productId).getSingleResult();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(CartLine cartLine) {
		
		try{
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(CartLine cartLine) {
		
		try{
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean remove(CartLine cartLine) {
		
		try{
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public List<CartLine> list(int cartId) {
		
		String selectCartLine = "FROM CartLine WHERE cartId = :cartId";
		
		try{								
			return sessionFactory.getCurrentSession().createQuery(selectCartLine, CartLine.class)
					.setParameter("cartId", cartId)
					.getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public CartLine get(int id) {
		CartLine cartLine=null;
		
		try{
			cartLine = sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return cartLine;
	}

	@Override
	public boolean updateCart(Cart cart) {

		try{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}
		
	@Override
	public List<CartLine> listAvailable(int cartId) {
		
		String selectCartLine = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		
		try{						
			return sessionFactory.getCurrentSession().createQuery(selectCartLine, CartLine.class)
					.setParameter("cartId", cartId)
					.setParameter("available", true)
					.getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}	

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		
		try{
			sessionFactory.getCurrentSession().persist(orderDetail);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void addProduct(Product product) {
		
		try{
			sessionFactory.getCurrentSession().persist(product);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}

	}

}
