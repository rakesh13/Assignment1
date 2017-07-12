package com.niit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestClass {
	
	private boolean result;
	@Test
	public boolean onlySelectClauseTest()
	{
		try
		{
		String query="select *";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();
		
		Assert.assertEquals("Test Succeeded","*","Invalid query");
		result=true;
		}
		catch(AssertionError ae)
		{
			result=false;
		}
		return result;
	}
	
	@Test
	public boolean selectAllColumnTest()
	{
		try
		{
		List<String> actualValue=new ArrayList<>();
		List<String> expectedValue=new ArrayList<>();
		actualValue.add("*");
		actualValue.add("demo.csv");
		String query="select * from demo.csv";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();;
		//("select * from login.csv");
		for(String value:queryParameter.getFields())
		{
			expectedValue.add(value.trim());
		}
		expectedValue.add(queryParameter.getCsvfile().trim());
		Assert.assertEquals("Test Succeeded",actualValue,expectedValue);
		result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		return result;
	}
	
	@Test
	public boolean selectToFromTest()
	{
		try
		{
		List<String> actualValue=new ArrayList<>();
		List<String> expectedValue=new ArrayList<>();
		actualValue.add("username");
		actualValue.add("password");
		actualValue.add("demo.csv");
		String query="select username,password from demo.csv";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();;
		//("select * from login.csv");
		for(String value:queryParameter.getFields())
		{
			expectedValue.add(value.trim());
		}
		expectedValue.add(queryParameter.getCsvfile().trim());
		Assert.assertEquals("Test Succeeded",actualValue,expectedValue);
		result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		return result;
	}
	@Test
	public boolean selectToWhereTest()
	{
		try
		{
		List<String> actualValue=new ArrayList<>();
		List<String> expectedValue=new ArrayList<>();
		String query="select username,password from demo.csv where username=rakesh";
		actualValue.add("username=rakesh");
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();;
		for(String value:queryParameter.getWhereConditions())
		{
			expectedValue.add(value);
		}
		//("select * from login.csv");
		Assert.assertEquals("Test Succeeded",actualValue,expectedValue);
		result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		return result;
	}
	@Test
	public boolean groupByClauseTest()
	{
		try
		{
		String query="select username,password from demo.csv where username=rakesh group by username";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();
		Assert.assertEquals("Test Succeeded","username",queryParameter.getGroupClause());
		result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		return result;
	}
	@Test
	public boolean orderByClauseTest()
	{
		try
		{
		String query="select username,password from demo.csv where username=rakesh group by username order by username";
		ParseQuery parseQuery=new ParseQuery(query);
		Query queryParameter=parseQuery.createQuery();
		Assert.assertEquals("Test Succeeded","username",queryParameter.getOrderClause());
		result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		return result;
	}
}
