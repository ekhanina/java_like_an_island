package ru.stqa.island.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="mantis_user_table")
public class UserData {

    @Id
    @Column(name="username")
    private String user;
    @Column(name="email")
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String set) {
        this.user=set;
    }
}
