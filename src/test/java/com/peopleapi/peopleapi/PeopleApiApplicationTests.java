package com.peopleapi.peopleapi;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peopleapi.peopleapi.controller.PeopleController;
import com.peopleapi.peopleapi.exception.people.PeopleNotFoundException;
import com.peopleapi.peopleapi.model.People;
import com.peopleapi.peopleapi.repository.PeopleRepository;

import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleApiApplicationTests {

	@Autowired
	private PeopleController peopleController;

	@Autowired	
	private PeopleRepository peopleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testListAllPeopleControllerEmptyArray()
	{
		List<People> listAll = peopleController.listAll();
		assertEquals(listAll, new ArrayList<>());
	}

	@Test
	void testListAllPeopleController()
	{
		People people = new People();
		people.setName("Teste");
		people.setBirthdayDate(new Date(2000, Calendar.AUGUST,01));

		peopleRepository.save(people);

		List<People> listAll = peopleController.listAll();
		assertEquals(listAll.toArray().length, 1);

		People secondPeople = new People();
		secondPeople.setName("Teste");
		secondPeople.setBirthdayDate(new Date(2002, Calendar.AUGUST,01));

		peopleRepository.save(secondPeople);

		List<People> secondListAll = peopleController.listAll();
		assertEquals(secondListAll.toArray().length, 2);
	}
	

	@Test()
	void testShowExceptionMessage()
	{
		Long id = new Long(1); 
		PeopleNotFoundException exception = assertThrows(PeopleNotFoundException.class, () -> peopleController.show(id));
		String expectedMessage = "People not found with ID " + id + "!";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test()
	@SneakyThrows
	void testShow()
	{

		Long id = new Long(1);
		String name = "Teste";
		Date date = new Date(2000, Calendar.AUGUST,01);

		People people = new People();
		people.setId(id);
		people.setName(name);
		people.setBirthdayDate(date);
		peopleRepository.save(people);
		
		People peopleShow = peopleController.show(id);
		assertEquals(id, peopleShow.getId());
		assertEquals(name, peopleShow.getName());
		assertEquals(date, peopleShow.getBirthdayDate());
	}

	@Test()
	@SneakyThrows
	void testPost()
	{
		Long id = new Long(1);
		String name = "Teste";
		Date date = new Date(2000, Calendar.AUGUST,01);

		People people = new People();
		people.setId(id);
		people.setName(name);
		people.setBirthdayDate(date);
		
		People peoplePost = peopleController.savePeople(people);
		assertEquals(id, peoplePost.getId());
		assertEquals(name, peoplePost.getName());
		assertEquals(date, peoplePost.getBirthdayDate());
	}

	@Test()
	void testUpdateExceptionMessage()
	{
		Long id = new Long(1); 
		PeopleNotFoundException exception = assertThrows(PeopleNotFoundException.class, () -> peopleController.update(id, new People()));
		String expectedMessage = "People not found with ID " + id + "!";
		assertEquals(expectedMessage, exception.getMessage());
	}

}
