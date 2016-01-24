package edu.usu.cs.oo.filter;

import edu.usu.cs.oo.Person;

public class CompositeOrPersonFilter implements IPersonFilter {
	
	IPersonFilter _filter1;
	IPersonFilter _filter2;
	
	
	public CompositeOrPersonFilter(IPersonFilter filter1, IPersonFilter filter2)
	{
		_filter1 = filter1;
		_filter2 = filter2;
	}
	
	
	@Override
	public boolean meetsCriteria(Person person) {
		
		
		return _filter1.meetsCriteria(person) || _filter2.meetsCriteria(person);
		
		
	}
	
}
