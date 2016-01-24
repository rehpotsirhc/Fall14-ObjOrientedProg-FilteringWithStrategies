package edu.usu.cs.oo.filter;

import edu.usu.cs.oo.Person;

public class AgeFilter implements IPersonFilter{
	
	
	private int _age;

	private Enum _operator;
	
	
	public AgeFilter(int age, Operator operator)
	{
		_age = age;
		_operator = operator;
	
	}
	
	@Override
	public boolean meetsCriteria(Person person)
	{
	
		if(_operator == Operator.greaterThan) return person.getAge() > _age;
		else if(_operator == Operator.lessThan) return person.getAge() < _age;
		else return person.getAge() == _age;
		
	 
	}

}
