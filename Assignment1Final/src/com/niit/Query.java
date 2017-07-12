package com.niit;

import java.util.List;

import org.junit.Test;

public class Query {

	private List<String> fields;
	private String csvfile;
	private List<String> whereConditions;
	private String groupClause;
	private String orderClause;
	
	public Query() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getFields() {
		
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
		
	}

	public String getCsvfile() {
		return csvfile;
	}

	public void setCsvfile(String csvfile) {
		this.csvfile = csvfile;
	}

	public List<String> getWhereConditions() {
		return whereConditions;
	}

	public void setWhereConditions(List<String> whereConditions) {
		this.whereConditions = whereConditions;
	}

	public String getGroupClause() {
		return groupClause;
	}

	public void setGroupClause(String groupClause) {
		this.groupClause = groupClause;
	}

	public String getOrderClause() {
		return orderClause;
	}

	public void setOrderClause(String orderClause) {
		this.orderClause = orderClause;
	}

	
	
	
}
