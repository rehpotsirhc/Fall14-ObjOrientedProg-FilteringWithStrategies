package edu.usu.cs.oo.filter;

public class FirstNameFilter extends StringFilter{

	public FirstNameFilter(String firstName, Operator operator) {
		
		
		super(firstName, operator, PersonProperty.firstName);


	}

}
