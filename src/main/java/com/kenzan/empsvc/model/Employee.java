package com.kenzan.empsvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Note: Not using Lombok. I like Lombok, but have seen that there are
 *  difficulties sometimes with IDE plugins. I want to ensure that this
 *  demo works without difficulties, so getters/setters are explicitly defined.
 */
@Entity
@Access(value= AccessType.FIELD)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="middleInitial")
    private Character middleInitial;

    @Column(name="lastName")
    private String lastName;

    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name="dateOfEmployment")
    private LocalDate dateOfEmployment;

    @Column(name="status")
    private EmployeeStatusEnum status = EmployeeStatusEnum.ACTIVE;

    protected Employee(){}

    public Employee( String firstName, Character middleInitial,
                     String lastName, LocalDate dob, LocalDate doe,
                     EmployeeStatusEnum empStatus ){
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.dateOfEmployment = doe;
        this.status = empStatus;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Character getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public EmployeeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"firstName\": \"" + firstName + '\"' +
                ", \"middleInitial\": \"" + middleInitial + "\"" +
                ", \"lastName\": \"" + lastName + "\"" +
                ", \"dateOfBirth\": \"" + dateOfBirth + "\"" +
                ", \"dateOfEmployment\": \"" + dateOfEmployment + "\"" +
                ", \"status\": \"" + status + "\"" +
                '}';
    }
}
