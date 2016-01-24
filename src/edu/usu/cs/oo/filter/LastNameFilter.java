package edu.usu.cs.oo.filter;

public class LastNameFilter extends StringFilter{
	
	public LastNameFilter(String lastName, Operator operator) 
	{
		super(lastName, operator, PersonProperty.lastName);
	}

}
