package com.iba.storebackend.dao;

import java.util.List;

import com.iba.storebackend.dto.Cart;
import com.iba.storebackend.dto.CartLine;
import com.iba.storebackend.dto.OrderDetail;
import com.iba.storebackend.dto.Product;

public interface CartLineDAO {
	
	public List<CartLine> list(int cartId);
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	public void addProduct(Product product);
	
	//updating the cart
	boolean updateCart(Cart cart);
	
	
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	// adding order details
	boolean addOrderDetail(OrderDetail orderDetail);

}
