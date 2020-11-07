package org.prickads.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Advertentie {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categorie categorie;

    private String naam;

    private String omschrijving;

    private double prijs;

    private boolean isVerkocht;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public Advertentie() {
    }

    public Advertentie(String naam, Categorie categorie, String omschrijving, double prijs, boolean isVerkocht, User user) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.isVerkocht = isVerkocht;
        this.user = user;
        this.categorie = categorie;
    }

    public boolean getVerkocht() {
        return isVerkocht;
    }
}
