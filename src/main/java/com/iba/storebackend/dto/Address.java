package com.iba.storebackend.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Veuillez sasisir votre adresse !")	
	private String address;
	@NotBlank(message = "Veuillez sasisir le nom de la ville !")
	private String city;				
	@NotBlank(message = "Veuillez sasisir le nom de votre pays !")
	private String country;
	@NotBlank(message = "Veuillez sasisir le code postal !")
	@Min(value = 4, message = "Le code postal doit etre composé de 4 chiffres")
	@Pattern(regexp="(^[0-9]{4})", message = "Le code postal ne contient que des chiffres")
	@Column(name ="postal_code")		
	private String postalCode;
	@Column(name="is_shipping")
	private boolean shipping;
	@Column(name="is_billing")
	private boolean billing;
	
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isShipping() {
		return shipping;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	public boolean isBilling() {
		return billing;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", city=" + city + ", country="
				+ country + ", postalCode=" + postalCode + ", shipping=" + shipping + ", billing=" + billing + ", user="
				+ user + "]";
	}
	
	

}
