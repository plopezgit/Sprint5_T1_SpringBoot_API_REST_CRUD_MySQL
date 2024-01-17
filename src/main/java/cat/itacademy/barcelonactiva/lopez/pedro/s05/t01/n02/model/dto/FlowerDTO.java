package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;



import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
    @Schema(description = "Flower entity/DTO identification attribute.", example = "1", required = true)
    private int id;
    @Schema(description = "Flower entity/DTO naming attribute.", example = "Tulip", required = true)
    @NotEmpty
    private String name;
    @Schema(description = "Flower entity/DTO country attribute.", example = "Spain", required = true)
    @NotEmpty
    private String country;
    @Schema(description = "Flower entity/DTO type(EURO/NOT EURO) attribute.", example = "Euro", required = true)
    private String type;
    @JsonIgnore
    private final List<String> EUROPEAN_COUNTRIES_REFERENCE = List.of("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany",
            "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain, Sweden");

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






