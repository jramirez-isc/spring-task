package com.leantech.task.service.interfaces;

import com.leantech.task.model.Person;

public interface PersonService {

  Person updatePerson(Person personDB, Person personReq);
}
