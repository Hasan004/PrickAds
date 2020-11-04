package Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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


}
