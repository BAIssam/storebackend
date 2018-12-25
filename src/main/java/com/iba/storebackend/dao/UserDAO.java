package com.iba.storebackend.dao;

import java.util.List;

import com.iba.storebackend.dto.Address;
import com.iba.storebackend.dto.Cart;
import com.iba.storebackend.dto.User;

public interface UserDAO {
	
	//user related operation
	boolean addUser(User user);
	User get(int id);
	User getByEmail(String email);
	
	//address  related operation
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getAddress(int addressId);		
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	
	//cart related operation
	boolean addCart(Cart cart);

}
