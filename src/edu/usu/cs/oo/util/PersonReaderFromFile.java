package edu.usu.cs.oo.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.usu.cs.oo.Person;

public class PersonReaderFromFile {
	
	public static List<Person> getPersonListFromFile() throws FileNotFoundException
	{
		List<Person> personList = new LinkedList<Person>();
		
		FileReader fr = new FileReader("PersonList.txt");
		Scanner s = new Scanner(fr);
		
		s.nextLine(); //ignore this one;
		
		while(s.hasNextLine())
		{
			String line =  s.nextLine();
			line = line.replace(" ", "");
			String[] data = line.split(",");
			
			int id = Integer.parseInt(data[0]);
			String firstName = data[1];
			String lastName = data[2];
			String dob = data[3];
			String gender = data[4];
			String q1 = data[5];
			String q2 = data[6];
			String q3 = data[7];
			String q4 = data[8];
			
			Calendar cal = Calendar.getInstance();
			String[] dobData = dob.split("/");
			cal.set(Calendar.MONTH, Integer.parseInt(dobData[0]) - 1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobData[1]));
			cal.set(Calendar.YEAR, Integer.parseInt(dobData[2]));
			
			Calendar now = Calendar.getInstance();
			
			long dif = now.getTimeInMillis() - cal.getTimeInMillis();
			long millisPerYear = 1000l*60l*60l*24l*365l;
			long yearsOld = (long) (dif / millisPerYear);
			
			
			Person p = new Person(firstName, lastName, gender, (int) yearsOld, dob, q1, q2, q3, q4);
		//System.out.println(p);
			
			personList.add(p);
			
			
		}
		s.close();
		
		return personList;
	}

}
