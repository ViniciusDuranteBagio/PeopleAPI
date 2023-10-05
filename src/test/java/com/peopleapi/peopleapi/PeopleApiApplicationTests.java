package com.peopleapi.peopleapi;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peopleapi.peopleapi.controller.AddressController;
import com.peopleapi.peopleapi.controller.PeopleController;
import com.peopleapi.peopleapi.exception.address.AddressNotFoundException;
import com.peopleapi.peopleapi.exception.people.PeopleNotFoundException;
import com.peopleapi.peopleapi.model.Address;
import com.peopleapi.peopleapi.model.People;
import com.peopleapi.peopleapi.repository.AddressRepository;
import com.peopleapi.peopleapi.repository.PeopleRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleApiApplicationTests {

	@Autowired
	private PeopleController peopleController;

	@Autowired
	private AddressRepository addressRepository;


	@Autowired
	private AddressController addressController ;

	@Autowired	
	private PeopleRepository peopleRepository;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp() {
		peopleRepository.deleteAll();
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
		Long id = new Long(10000); 
		PeopleNotFoundException exception = assertThrows(PeopleNotFoundException.class, () -> peopleController.show(id));
		String expectedMessage = "People not found with ID " + id + "!";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test()
	void testShow() throws PeopleNotFoundException
	{
		String name = "Teste";
		Date date = new Date(2000, Calendar.AUGUST,01);

		People people = new People();
		people.setName(name);
		people.setBirthdayDate(date);
		People insertedPeople = peopleRepository.save(people);
		
		People peopleShow = peopleController.show(insertedPeople.getId());
		
		assertEquals(insertedPeople.getId(), peopleShow.getId());
		assertEquals(name, peopleShow.getName());
		assertEquals(date, peopleShow.getBirthdayDate());
	}

	@Test()
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

	@Test
   
    void testUpdate() throws PeopleNotFoundException
	{
		Date date  = new Date(1985, Calendar.NOVEMBER, 21);
		String newName = "teste";
        People people = new People();
        people.setName("People");
        people.setBirthdayDate(date);

        People peopleInserted = peopleRepository.save(people);
		peopleInserted.setName(newName);
		People peopleUpdated = peopleController.update(people.getId(), peopleInserted);

        assertEquals(newName, peopleUpdated.getName());
    }


	@Test()
	void testeFindAllFromPeopleShouldReturnException() throws PeopleNotFoundException
	{
		Long id = new Long(1); 
		PeopleNotFoundException exception = assertThrows(PeopleNotFoundException.class, () -> addressController.showAddress(id));
		String expectedMessage = "People not found with ID " + id + "!";
		assertEquals(expectedMessage, exception.getMessage());
	}


	@Test
    void testUpdateAddress() throws AddressNotFoundException
	{
        Address address = new Address();
        address.setCity("Test City");
        address.setMain(true);
        address.setNumber(1);
        address.setPublicPlace("Center");
        address.setZipCode(88870000);

        Address addressDB = addressRepository.save(address);

		String city = "new Test City";
		Integer number = 2;
		String publicPlace = "Center 2";
		Integer zipCode = 88870000;

        addressDB.setCity(city);
        addressDB.setMain(true);
        addressDB.setNumber(number);
        addressDB.setPublicPlace(publicPlace);
        addressDB.setZipCode(zipCode);

        Address newAddress = addressController.update(addressDB.getId(), addressDB);
		
        assertEquals(city, newAddress.getCity());
        assertTrue(newAddress.getMain());
        assertEquals(number, newAddress.getNumber());
        assertEquals(publicPlace, newAddress.getPublicPlace());
        assertEquals(zipCode, newAddress.getZipCode());
    }	
}
