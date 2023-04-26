package com.ezlearner.TO;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.google.gson.annotations.Expose;

public class Person {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private Integer hr;
  @Expose
  private long timespan = 1000L * LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
  public long getTimespan() {
	return timespan;
}

/*
 * public void setTimespan(long timespan) { this.timespan = timespan; }
 */

public Integer getHr() {
	return hr;
}

public void setHr(Integer hr) {
	this.hr = hr;
}

public Integer getSpo2() {
	return spo2;
}

public void setSpo2(Integer spo2) {
	this.spo2 = spo2;
}

/**
 * @param id
 * @param firstName
 * @param lastName
 * @param email
 * @param gender
 * @param hr
 * @param spo2
 * @param age
 */
public Person(Integer id, String firstName, String lastName, String email, String gender, Integer hr, Integer spo2,
		Integer age) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.gender = gender;
	this.hr = hr;
	this.spo2 = spo2;
	this.age = age;
}

/**
 * 
 */
public Person() {
	
}

private Integer spo2;
  private Integer age;

  public Person(Integer id, String firstName, String lastName, String email, String gender,
      Integer age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.age = age;
  }

  public void setId(Integer id) {
	this.id = id;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public void setEmail(String email) {
	this.email = email;
}

public void setGender(String gender) {
	this.gender = gender;
}

public void setAge(Integer age) {
	this.age = age;
}

public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", gender='" + gender + '\'' +
        ", age=" + age +
        '}';
  }
}