//https://www.journaldev.com/9131/soap-webservices-in-java-example-eclipse
package com.journaldev.jaxws.service;

import com.journaldev.jaxws.beans.Person;

public interface PersonService {

	public boolean addPerson(Person p);
	
	public boolean deletePerson(int id);
	
	public Person getPerson(int id);
	
	public Person[] getAllPersons();
}
