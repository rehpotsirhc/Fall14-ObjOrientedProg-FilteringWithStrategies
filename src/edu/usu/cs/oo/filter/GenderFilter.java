package edu.usu.cs.oo.filter;

public class GenderFilter extends StringFilter {

	
	public GenderFilter(String gender)
	{
		super(gender, Operator.equalTo, PersonProperty.gender);
	}
}
