package com.leantech.task.service;

import com.leantech.task.model.Person;
import com.leantech.task.repository.PersonRepository;
import com.leantech.task.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Service layer for Person.
 * Contains only a method to update based on an Employee update request.
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  /**
   * Updates a Person object in the DB based on the fields of personReq.
   * If the request contains a null value it will delete the corresponding Person in DB if exists.
   *
   * @param personDB  object to be updated
   * @param personReq object with the new values for the Person object
   * @return Person object with its values updated in the DB.
   */
  @Transactional
  @Override
  public Person updatePerson(Person personDB, Person personReq) {
    if (personReq == null) {
      if (personDB != null) personRepository.delete(personDB);
      return null;
    }
    if (personDB == null) {
      personDB = new Person();
    }
    personDB.setName(personReq.getName());
    personDB.setLastName(personReq.getLastName());
    personDB.setAddress(personReq.getAddress());
    personDB.setCellphone(personReq.getCellphone());
    personDB.setCityName(personReq.getCityName());
    return personRepository.save(personDB);
  }
}