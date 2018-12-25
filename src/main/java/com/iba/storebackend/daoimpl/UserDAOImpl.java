package com.iba.storebackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iba.storebackend.dao.UserDAO;
import com.iba.storebackend.dto.Address;
import com.iba.storebackend.dto.Cart;
import com.iba.storebackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {

		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public User get(int id) {

		return sessionFactory.getCurrentSession().get(User.class, Integer.valueOf(id));

	}

	@Override
	public User getByEmail(String email) {

		String selectUserByEmail = "FROM User WHERE email = :email";
		
		try{
			return sessionFactory.getCurrentSession()
					.createQuery(selectUserByEmail, User.class)
					.setParameter("email", email)
					.getSingleResult();
		}
		catch (Exception e) {
			//e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().update(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		return sessionFactory.getCurrentSession().get(Address.class, Integer.valueOf(addressId));		
	}

	@Override
	public Address getBillingAddress(int userId) {
		String strSelect = "FROM Address WHERE userId = :userId AND billing = :billing";
		
		try{
			return sessionFactory.getCurrentSession()
					.createQuery(strSelect, Address.class)
					.setParameter("userId", userId)
					.setParameter("billing", true)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		String strSelect = "FROM Address WHERE userId = :userId AND shipping = :shipping";
		
		try{
			return sessionFactory.getCurrentSession()
					.createQuery(strSelect, Address.class)
					.setParameter("userId", userId)
					.setParameter("shipping", true)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().persist(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
