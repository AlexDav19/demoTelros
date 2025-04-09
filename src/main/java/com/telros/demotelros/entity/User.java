package com.telros.demotelros.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "surName", nullable = false)
    private String surName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @OneToOne
    private Photo photo;

    public User(long id, String lastName, String firstName, String surName, LocalDate birthday, String email, String telephone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
    }

    public User(String lastName, String firstName, String surName, LocalDate birthday, String email, String telephone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(surName, user.surName) && Objects.equals(birthday, user.birthday) && Objects.equals(email, user.email) && Objects.equals(telephone, user.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, surName, birthday, email, telephone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
