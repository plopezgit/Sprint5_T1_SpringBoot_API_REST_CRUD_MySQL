package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
    private int id;
    private String name;
    private String country;
    private String type;
    @JsonIgnore
    private final List<String> EUROPEAN_COUNTRIES_REFERENCE = List.of("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany",
            "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain and Sweden");

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry (String country) {
        this.country = country;
        setType(country);
    }

    public void setType (String country) {
        if (EUROPEAN_COUNTRIES_REFERENCE.stream().anyMatch(c -> c.equalsIgnoreCase(country))) this.type = "Euro";
        else this.type = "Not Euro";
    }

}






