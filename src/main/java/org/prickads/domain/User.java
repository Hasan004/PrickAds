package org.prickads.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long user_id;

    private String naam;

    private String password;

    private String email;

    private String adres;

    private String postcode;

    private String woonplaats;

    private boolean isAkkoord;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Advertentie> advertenties = new ArrayList<>();

    public User() {
    }

    public User(String naam, String password, String email, String adres, String postcode, String woonplaats, boolean isAkkoord) {
        this.naam = naam;
        this.password = password;
        this.email = email;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.isAkkoord = isAkkoord;
    }

}
