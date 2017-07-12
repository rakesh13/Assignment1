package com.niit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestClass {
	
	@Test
	public void selectToFromTest()
	{
		
		List<String> actualValue=new ArrayList<>();
		List<String> expectedValue=new ArrayList<>();
		actualValue.add("username");
		actualValue.add("password");
		String query="select username,password from demo.csv";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=
		parseQuery.createQuery();//("select * from login.csv");
		/*for(String x:queryParameter.getFields())
		{
			expectedValue.add(x);
		}*/
		Assert.assertEquals("Test Succeeded", query, );
	}
	@Test
	public void selectToWhereTest()
	{
		
	}
}
