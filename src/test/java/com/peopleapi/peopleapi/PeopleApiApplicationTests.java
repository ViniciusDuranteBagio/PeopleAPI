package com.peopleapi.peopleapi;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peopleapi.peopleapi.controller.PeopleController;
import com.peopleapi.peopleapi.model.People;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleApiApplicationTests {

	@Autowired
	private PeopleController peopleController;

	@Test
	void contextLoads() {
	}

	@Test
	void testListAllPeopleController()
	{
		List<People> listAll = peopleController.listAll();
		assertEquals(listAll, new ArrayList<>());
	}

}
