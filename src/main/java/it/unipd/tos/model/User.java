////////////////////////////////////////////////////////////////////
// Federico Ballarin 1123718
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private long id;
    private String name;
    private String address;
    private LocalDate birthDate;

    public User( long id, String name, String address, LocalDate birthDate) {
        this.id=id;
        this.name=name;
        this.address=address;
        this.birthDate=birthDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isUnder18() {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if(age<18) {
            return true;
        } else { 
            return false;
        }
    }
}
