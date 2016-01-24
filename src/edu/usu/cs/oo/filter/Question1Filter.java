package edu.usu.cs.oo.filter;

public class Question1Filter extends StringFilter{
	
	
	public Question1Filter(String question1)
	{
		super(question1, Operator.equalTo, PersonProperty.question1);
	}

}
