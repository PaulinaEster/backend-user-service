package com.paulina.backenduserservice.shared.dtos;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class UserDto {
    // é o q mando para a base de dados
    @Id
    private String id;

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String password, String encryptedPassword) {
        this(null, firstName, lastName, email, password, encryptedPassword, null);
    }

    public UserDto(String id, String firstName, String lastName, String email, String password,String encryptedPassword, String userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setId() {
        int result = id != null ? id.hashCode() : 0;
        this.id = String.valueOf(result = 31 * result + ( firstName != null ? firstName.hashCode() + lastName.hashCode() : 0));
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto user = (UserDto) o;
        return id.equals(user.id) && userId.equals(user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(encryptedPassword, user.encryptedPassword);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( firstName != null ? firstName.hashCode() + lastName.hashCode() : 0);
        return result;
    }

    // @Override
    // public int hashCode() {
    //    int result = id != null ? id.hashCode() : 0;
    //     result = 31 * result + (name != null ? name.hashCode() : 0);
    //     return result;
    // }
}
