package edu.usu.cs.oo.filter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import edu.usu.cs.oo.Person;

public abstract class StringFilter implements IPersonFilter {

	private String _s;
	
	//While this class could be used directly....
	//Not all combinations of Operator and PersonProperty make sense, however when they don't make sense one or the other is ignored
	//For example, greater than and less than for gender doesn't make sense and are ignored
	//Because this might be confusing, this class is abstract. Inheriting classes will just have to create an appropriate constructor and call its super
	
	
	private Operator _operator;
	private PersonProperty _property;

	public StringFilter(String s, Operator operator, PersonProperty property) {
		_s = s;
		_operator = operator;
		_property = property;
	}

	@Override
	public boolean meetsCriteria(Person person) {
		
		if(_s == null) return false;
		
		_s = _s.toLowerCase();
		
		int c = 0;
		
		
		if(_property == PersonProperty.firstName)
		{
			c = person.getFirstName().toLowerCase().compareTo(_s);
		}
		else if(_property == PersonProperty.lastName)
		{
			c = person.getLastName().toLowerCase().compareTo(_s);
		}
		else if(_property == PersonProperty.gender)
		{
			 return person.getGender().equalsIgnoreCase(_s);
			
		}
		else if(_property == PersonProperty.question1)
		{
			if(person.getQuestions().length > 0) return person.getQuestions()[0].equalsIgnoreCase(_s);
			else return false;
		}
		else if(_property == PersonProperty.question2)
		{
			if(person.getQuestions().length > 1) return person.getQuestions()[1].equalsIgnoreCase(_s);
			else return false;
		}
		else if(_property == PersonProperty.question3)
		{
			if(person.getQuestions().length > 2) return person.getQuestions()[2].equalsIgnoreCase(_s);
			else return false;
		}
		else if(_property == PersonProperty.question4)
		{
			if(person.getQuestions().length > 3) return person.getQuestions()[3].equalsIgnoreCase(_s);
			else return false;
		}
		else if(_property == PersonProperty.birthday)
		{
			try
			{
				String format = "MM/dd/yyy";
				LocalDate passed = DateTimeFormat.forPattern(format).parseLocalDate(_s);
				LocalDate birthday = DateTimeFormat.forPattern(format).parseLocalDate(person.getBirthday());
			
				c = birthday.compareTo(passed);
			}
			catch(Exception e)
			{
				return false;
			}
			
		}
		
		if(_operator == Operator.greaterThan) return c > 0;
		else if(_operator == Operator.lessThan) return c < 0;
		else return c == 0;
		
		
		
	}
}
