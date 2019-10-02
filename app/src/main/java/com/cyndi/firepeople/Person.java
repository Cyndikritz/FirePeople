package com.cyndi.firepeople;

public class Person {

    String firstName;
    String surname;
    String fireReason;

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFireReason() {
        return fireReason;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFireReason(String fireReason) {
        this.fireReason = fireReason;
    }

   // @androidx.annotation.NonNull
    @Override
    public String toString() {
        return firstName+" "+surname;
    }
}
