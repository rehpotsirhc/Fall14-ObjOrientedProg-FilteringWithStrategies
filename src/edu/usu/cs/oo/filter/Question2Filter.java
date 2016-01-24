package edu.usu.cs.oo.filter;


	
	
	public class Question2Filter extends StringFilter{
		
		
		public Question2Filter(String question1)
		{
			super(question1, Operator.equalTo, PersonProperty.question1);
		}

	}


