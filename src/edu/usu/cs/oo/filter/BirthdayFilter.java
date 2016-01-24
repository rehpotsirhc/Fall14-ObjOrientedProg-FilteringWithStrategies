package edu.usu.cs.oo.filter;

public class BirthdayFilter extends StringFilter{

	
	
	public BirthdayFilter(String birthday, Operator operator)
	{
		super(birthday, operator, PersonProperty.birthday);
	}
}
