package io.github.bookcrawler.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String profileName;

    private Profile() {

    }

    public Profile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }
}
