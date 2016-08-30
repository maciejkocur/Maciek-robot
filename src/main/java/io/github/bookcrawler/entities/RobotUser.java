package io.github.bookcrawler.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class RobotUser implements Serializable {
    @Id
    private String login;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="robotuser_profile", joinColumns=@JoinColumn(name="user_login"), inverseJoinColumns=@JoinColumn(name="profile_id"))
    private Set<Profile> profiles;


    private RobotUser() {
    }

    public RobotUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public RobotUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RobotUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RobotUser profile(Set<Profile> profiles) {
        this.profiles = profiles;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<Profile> getProfile() {
        return profiles;
    }
}
