package com.niit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.niit.Criteria;

public class ParseQuery {

	String query;
	Query queryParameter;
	public ParseQuery() {
		query="";
		queryParameter=new Query();
	}
	public ParseQuery(String query) {
		queryParameter=new Query();
		this.query=query;
	}
	public void createQuery()
	{
		String pattern = "select(.*)from(.*)";
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.

		List<String> fieldlist = new ArrayList<>();
		String filename = null;
		Matcher m = r.matcher(query);

		if (m.find()) {
			if (m.group(1).contains("*")) {
				fieldlist.add(m.group(1));
			} else {
				String[] fielditem = m.group(1).split(",");
				for (String field : fielditem) {
					fieldlist.add(field);

				}

			}

			filename = m.group(2);

			for (String f : fieldlist) {
				System.out.println(f);
			}
			System.out.println(filename);

			queryParameter.setFields(fieldlist);
			queryParameter.setCsvfile(filename);
		}
		
		
		String[] splitQuery = null;
		
		
		if (query.contains("where")) {
			splitQuery = query.split("where");
			this.whereClause(splitQuery[1]);
			query=splitQuery[0];

		}
		

		else if (query.contains("group by")) {
			splitQuery =query.split("group by");
			this.groupbyClause(splitQuery[1]);
			query=splitQuery[0];
		}
		else if (query.contains("order by")) {
			splitQuery = query.split("order by");
			this.orderby(splitQuery[1]);
			//query=splitQuery[0];
		}
	}
	public Query querySelectToFrom(String parameter) {

		String pattern = "select(.*)from(.*)";
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.

		List<String> fieldlist = new ArrayList<>();
		String filename = null;
		Matcher m = r.matcher(parameter);

		if (m.find()) {
			if (m.group(1).contains("*")) {
				fieldlist.add(m.group(1));
			} else {
				String[] fielditem = m.group(1).split(",");
				for (String field : fielditem) {
					fieldlist.add(field.trim());

				}

			}

			filename = m.group(2);

			for (String f : fieldlist) {
				System.out.println(f);
			}
			System.out.println(filename);

			queryParameter.setFields(fieldlist);
			queryParameter.setCsvfile(filename);
		}
		return queryParameter;
	}

	private void whereClause(String whereParam) {
		// TODO Auto-generated method stub

		String pattern = "(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(whereParam);
		String[] whereArray = null;
		List<String> conditions=new ArrayList<>();
		String whereString = null;
		Criteria criteria = new Criteria();

		if (m.find()) {
			whereString = m.group(1);
			System.out.println(whereString);

			whereArray = whereString.split(" ");
			for (String condition : whereArray) {
				// System.out.println(c);
				if (!(condition.equals("and") || condition.equals("or"))) {
					String pattern1 = "(([A-Z])\\w+)$";
					Pattern pattern2 = Pattern.compile(pattern1);
					Matcher m1 = pattern2.matcher(condition);
					if (m.find()) {
						String searchValue = m.group();
						criteria.setValue(searchValue);
						// System.out.println(column);
					}
					String pattern3 = "(([A-Z])\\w+)";
					Pattern p1 = Pattern.compile(pattern3);
					Matcher m2 = p1.matcher(condition);
					if (m1.find()) {
						String searchColumn = m.group();
						// System.out.println(column);
						criteria.setColumn(searchColumn);

					}
					conditions.add(condition);
					
				}
			}
			queryParameter.setWhereConditions(conditions);

		}

	}

	private void groupbyClause(String groupByParam) {
		String pattern = "(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(groupByParam);

		String groupByString = null;

		if (m.find()) {
			groupByString = m.group(1);

		}

		System.out.println(groupByString);
		

	}

	private void havingClause(String havingParam) {
		// TODO Auto-generated method stub

		String pattern = "(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(havingParam);

		String havingString = null;

		if (m.find()) {
			havingString = m.group(1);

		}
System.out.println(havingString);
		//queryParameter.setHaving(havingString);

	}

	private void orderby(String orderbyParam) {

		String pattern = "(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(orderbyParam);

		String orderbyString = null;

		if (m.find()) {
			orderbyString = m.group(1);

		}
		System.out.println(orderbyString);
		//queryParameter.setOrderby(orderbyString);

	}
}
