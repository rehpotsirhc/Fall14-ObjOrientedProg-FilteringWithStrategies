package edu.usu.cs.oo;

import java.util.Arrays;

public class Person {

	String firstName;
	String lastName;
	String gender;
	int age;
	String birthday;
	String[] questions;

	public Person(String firstName, String lastName, String gender, int age,
			String birthday, String q1, String q2, String q3, String q4) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.birthday = birthday;
		this.questions = new String[]{q1, q2, q3, q4};
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String[] getQuestions() {
		return questions;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", age=" + age + ", birthday="
				+ birthday + ", questions=" + Arrays.toString(questions) + "]";
	}
}
