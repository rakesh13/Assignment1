package com.niit;

public class MainClass {

	public static void main(String[] args) {
		boolean result;
		TestClass t=new TestClass();
		result=t.onlySelectClauseTest();
		if(result)
		{
			System.out.println("Test Succeded!!!");
		}
		else
		{
			System.out.println("Invalid Query/Problem in Processing Query...");
		}

	}

}
