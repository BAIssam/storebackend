package com.iba.storebackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.iba.storebackend.dao.CategoryDAO;
import com.iba.storebackend.dto.Category;


public class CategoryTestCase {
	
private static AnnotationConfigApplicationContext context;
		
	private static CategoryDAO categoryDAO;
		
	private Category category;	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.iba.storebackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	@Test
	public void testAddCategory() {
		
		category = new Category();
		
		//category.setId(7);
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_105.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		
	}
	
/*	
	@Test
	public void testGetCategory() {				
		
		assertEquals("Successfully fetch a category inside the table!",1,categoryDAO.get(1).getId());
				
	}
*/
/*	
	@Test
	public void testUpdateCategory() {
		
		category = categoryDAO.get(6);			
		category.setName("kontra");
		
		assertEquals("Successfully updateded a category inside the table!",true,categoryDAO.update(category));
		
		
	}
	*/
}
