package com.niit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.niit.Criteria;

public class ParseQuery {

	private String query;
	private Query queryParameter;

	public ParseQuery() {
		query = "";
		queryParameter = new Query();
	}

	public ParseQuery(String query) {
		queryParameter = new Query();
		this.query = query;
	}

	private boolean validateQuery() {
		if (query.startsWith("select") && query.contains("from")) {
			return true;
		} else {
			return false;
		}
	}

	public Query createQuery() {
		if (validateQuery()) {

			String pattern = "select(.*)from(.*)";
			Pattern pattern1 = Pattern.compile(pattern);

			// Now create matcher object.

			List<String> fieldlist = new ArrayList<>();
			String filename = null;
			Matcher matcher = pattern1.matcher(query);

			if (matcher.find()) {
				if (matcher.group(1).contains("*")) {
					fieldlist.add(matcher.group(1));
				} else {
					String[] fielditem = matcher.group(1).split(",");
					for (String field : fielditem) {
						fieldlist.add(field);

					}

				}

				filename = matcher.group(2);

				queryParameter.setFields(fieldlist);
				queryParameter.setCsvfile(filename);
			}

			String[] splitQuery = null;

			if (query.contains("where")) {
				splitQuery = query.split("where");
				this.whereClause(splitQuery[1]);

				if (query.contains("group by")) {
					splitQuery = query.split("group by");
					this.groupbyClause(splitQuery[1]);

					if (query.contains("order by")) {
						splitQuery = query.split("order by");
						this.orderby(splitQuery[1]);

					}
				}
			}
		} else {
			System.exit(0);
		}
		return queryParameter;
	}

	private Query querySelectToFrom(String parameter) {

		String pattern = "select(.*)from(.*)";
		Pattern pattern1 = Pattern.compile(pattern);
		List<String> fieldlist = new ArrayList<>();
		String filename = null;
		Matcher matcher = pattern1.matcher(parameter);

		if (matcher.find()) {
			if (matcher.group(1).contains("*")) {
				fieldlist.add(matcher.group(1));
			} else {
				String[] fielditem = matcher.group(1).split(",");
				for (String field : fielditem) {
					fieldlist.add(field.trim());

				}
			}
			filename = matcher.group(2);
			queryParameter.setFields(fieldlist);
			queryParameter.setCsvfile(filename.trim());
		}
		return queryParameter;
	}

	private Query whereClause(String whereParam) {
		String pattern = "(.*)";
		Pattern pattern1 = Pattern.compile(pattern);
		Matcher matcher = pattern1.matcher(whereParam);
		List<String> conditions = new ArrayList<>();
		String whereString = null;
		Criteria criteria = new Criteria();

		if (matcher.find()) {
			whereString = matcher.group(1).trim();
			String[] whereArray = whereString.split(" ");
			for (String condition : whereArray) {

				if ((condition.equals("group")) || (condition.equals("order"))) {
					break;
				}
				String[] colName = condition.split("[>|>=|<|<=|=|!=]");
				int startIndex = condition.indexOf(colName[0]) + colName[0].length();
				int endIndex = condition.indexOf(colName[1]);
				String operator = condition.substring(startIndex, endIndex).trim();
				criteria.setColumn(colName[0].trim());
				criteria.setOperator(operator);
				criteria.setValue(colName[1].trim());
				conditions.add(condition);

			}

			queryParameter.setWhereConditions(conditions);

		}

		return queryParameter;
	}

	private Query groupbyClause(String groupByParam) {
		String pattern = "(.*)";
		Pattern pattern1 = Pattern.compile(pattern);
		Matcher matcher = pattern1.matcher(groupByParam);

		String[] groupByString = null;

		if (matcher.find()) {
			groupByString = matcher.group().split(" ");
			for (String group : groupByString) {
				if (group.equals("order")) {
					break;
				}
				queryParameter.setGroupClause(group);
			}

		}

		return queryParameter;

	}

	private void havingClause(String havingParam) {

		String pattern = "(.*)";
		Pattern pattern1 = Pattern.compile(pattern);
		Matcher matcher = pattern1.matcher(havingParam);

		String havingString = null;

		if (matcher.find()) {
			havingString = matcher.group(1);

		}

	}

	private Query orderby(String orderbyParam) {

		String pattern = "(.*)";
		Pattern pattern1 = Pattern.compile(pattern);
		Matcher matcher = pattern1.matcher(orderbyParam);

		String orderbyString = null;

		if (matcher.find()) {
			orderbyString = matcher.group();

		}
		queryParameter.setOrderClause(orderbyString.trim());
		return queryParameter;
	}
}
