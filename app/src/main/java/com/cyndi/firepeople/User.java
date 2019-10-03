package com.cyndi.firepeople;

public class User {

    private String firstName;
    private String surname;
    private String publisher;
    private String containerID;

    public User(){

    }

    public User(String name, String surname, String publisher, String containerID)
    {
        if(name.trim().equals(""))
        {
            name = "No Name";
        }
        if(surname.trim().equals(""))
        {
            surname = "No Surname";
        }
        firstName = name;
        this.surname = surname;
        this.publisher = publisher;
        this.containerID = containerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }
}
