package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
    @Schema(description = "Flower entity/DTO identification attribute.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private int id;
    @Schema(description = "Flower entity/DTO naming attribute.", example = "Tulip", requiredMode = Schema.RequiredMode.REQUIRED)
    //NotBlank: a constrained String is valid as long as it's not null, and the trimmed length is greater than zero.
    @NotBlank(message = "The flower name must not be null or empty.")
    private String name;
    @Schema(description = "Flower entity/DTO country attribute.", example = "Spain", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The flower country must not be null or empty.")
    private String country;
    @Schema(description = "Flower entity/DTO type(EURO/NOT EURO) attribute.", example = "Euro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;
    private final List<String> EUROPEAN_COUNTRIES_REFERENCE = List.of("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany",
            "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public void setCountry(String country) {
        this.country = country;
        setType(country);
    }

    public void setType(String country) {
        if (EUROPEAN_COUNTRIES_REFERENCE.stream().anyMatch(c -> c.equalsIgnoreCase(country))) this.type = "Euro";
        else this.type = "Not Euro";
    }

}






