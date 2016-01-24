package edu.usu.cs.oo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import edu.usu.cs.oo.filter.*;
import edu.usu.cs.oo.util.PersonReaderFromFile;

public class Driver {
	
	public static void main(String[] args) throws IOException
	{
		
		//Use the PersonGetter to get the list of Persons from the file. 
		List<Person> allPeople = PersonReaderFromFile.getPersonListFromFile();
		
		
		
		
		
		//Create a PersonFilter that uses a Strategy to filter the list according to age.
		
		
		//filter older than 26
		int age = 26;
		IPersonFilter filter = new AgeFilter(26, Operator.greaterThan);
		String output = "OLDER THAN 26\n";
		output += FilteredOutput(filter, allPeople);
	
		//filter younger than 26
		filter = new AgeFilter(26, Operator.lessThan);
		output += "YOUNGER THAN 26\n";
		output += FilteredOutput(filter, allPeople);
		
		//filter exactly 26
		filter = new AgeFilter(26, Operator.equalTo);
		output += "Exactly 26\n";
		output += FilteredOutput(filter, allPeople);
		
		
		
		
		
		
		
		
		//Create a CompositeOrPersonFilter that uses TWO strategies to filter either by gender OR by first name
		
		
		//first filter by a first name criteria(comes after "Chris" in alphabetical order)
		IPersonFilter filterFirstName = new FirstNameFilter("Chris", Operator.greaterThan);
		output += "First Name After \"Chris\" in Alphabetical Order \n";
		output += FilteredOutput(filterFirstName, allPeople);
		
		//then by a gender criteria
		IPersonFilter genderFilter = new GenderFilter("F");
		output += "IS FEMALE\n";
		output += FilteredOutput(genderFilter, allPeople);
		
		
		//now lets combine these filters
		//this list will include everybody whose first name comes after "Chris" OR is female
		IPersonFilter filterFirstNameOrFemale = new CompositeOrPersonFilter(filterFirstName, genderFilter);
		output += "First Name After \"Chris\" in Alphabetical Order OR IS Female \n";
		output += FilteredOutput(filterFirstNameOrFemale , allPeople);

		
		
		
		
		
		
		
		
		
		//Create a CompositeAndPersonFilter that uses two strategies to filter by Question 1 AND last name
		
		//filter by a last name criteria(comes before "Johnson" in alphabetical order)
		IPersonFilter filterLastName = new LastNameFilter("Johnson", Operator.lessThan);
		output += "Last Name Before \"Johnson\" in Alphabetical Order \n";
		output += FilteredOutput(filterLastName, allPeople);
		
		//filter by question1 = 1
		IPersonFilter filterQuestion1 = new Question1Filter("1");
		output += "Question1 = 1\n";
		output += FilteredOutput(filterQuestion1, allPeople);
		
		
		//combine filters
		//includes everybody whose last name comes before "Johnson" AND who answered 1 to Question1
		IPersonFilter filterLastNameANDQuestion1 = new CompositeAndPersonFilter(filterLastName, filterQuestion1);
		output += "Last Name Before \"Johnson\" in Alphabetical Order AND Question1 = 1\n";
		output += FilteredOutput(filterLastNameANDQuestion1, allPeople);
		
		
		
		
		
		
		
		
		//Create a filter that is a combination of at least two AndFilters and two OrFilters. Combine them however you want.
		//8 basic filters then?
		
		//first combine the OR and AND filters above, creating one with 4 basic filters
		
		
		
		//includes everybody who meets: (first name after "Chris" OR is female) AND (last name before "Johnson" AND Question1 = 1)
		IPersonFilter firstNameOrFemaleANDLastNameANDQuestion1 = new CompositeAndPersonFilter(filterFirstNameOrFemale, filterLastNameANDQuestion1);
		output += "(First Name After \"Chris\" in Alphabetical Order OR IS Female) AND (Last Name Before \"Johnson\" in Alphabetical Order AND Question1 = 1)\n";
		output += FilteredOutput(firstNameOrFemaleANDLastNameANDQuestion1, allPeople);
		
		
		
		//includes everybody born before December 31st, 1989
		IPersonFilter filterBirthday = new BirthdayFilter("12/31/1989", Operator.lessThan);
		
		//includes everybody born after January 1, 1980
		IPersonFilter filterBirthday2 = new BirthdayFilter("1/1/1980", Operator.greaterThan);
		
		
		//includes everybody born in the 80s
		IPersonFilter filterEightiesBabies = new CompositeAndPersonFilter(filterBirthday, filterBirthday2);
		
		
		//includes every male
		IPersonFilter genderFilter2 = new GenderFilter("M");
		
		//answered 0 for question 2
		IPersonFilter filterQuestion2 = new Question2Filter("0");
		
		
		
		//includes every male or answered 0 for question 2
		IPersonFilter filterMaleORQuestion2 = new CompositeOrPersonFilter(genderFilter2, filterQuestion2);
		
	
		//includes everybody who meets: (born in the 80s) OR (male or answered 0 for question 2)
		IPersonFilter filterEightiesBabiesORMaleORQuestion2 = new CompositeOrPersonFilter(filterEightiesBabies, filterMaleORQuestion2);
		output += " (born in the 80s) OR (male or answered 0 for question 2) \n";
		output += FilteredOutput(filterEightiesBabiesORMaleORQuestion2, allPeople);
		
		
		
		
		//includes everybody who meets: 
		//[(first name after "Chris" OR is female) AND (last name before "Johnson" AND Question1 = 1)] AND
		//[(born in the 80s) OR (male OR answered 0 for question 2)]
		IPersonFilter myMegaFilter = new CompositeAndPersonFilter(firstNameOrFemaleANDLastNameANDQuestion1,  filterEightiesBabiesORMaleORQuestion2);
		output += "[(first name after \"Chris\" OR is female) AND (last name before \"Johnson AND Question1 = 1)] AND [(born in the 80s) OR (male or answered 0 for question 2)]\n";
		output += FilteredOutput(myMegaFilter, allPeople);
		
		
		
		WriteFile("Output.txt", output);
		System.out.println(output);
		
		
	}
	
	
	private static String FilteredOutput(IPersonFilter filter, List<Person> allPeople)
	{
		String output = "";
		for(Person p : allPeople)
		{
			if(filter.meetsCriteria(p)) output += p.toString() + "\n";
		}
		output += "\n\n";
		return output;
		
	}
	
	private static void WriteFile(String fileName, String output) throws IOException
	{
		 Writer w = new FileWriter(fileName);
		 
		 w.write(output);
		
		w.close();
	}

}
