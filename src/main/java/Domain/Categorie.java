package Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categorie {

    @Id
    @GeneratedValue
    private long id;

    private String naam;

    @OneToMany(mappedBy = "categorie")
    private List<Advertentie> advertenties = new ArrayList<>();;

    public Categorie() {
    }

    public Categorie(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
