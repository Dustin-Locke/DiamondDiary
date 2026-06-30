package locke.dustin.diamonddiary.util;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "geo_location" )
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @NotBlank(message = "A location address is required." )
    private String name;

    @Embedded
    @Valid
    @NonNull
    private Address address;

    private Double latitude;

    private Double longitude;

    public GeoLocation (
            String name,
            @NonNull Address address,
            Double latitude,
            Double longitude ) {

        this.name      = name;
        this.address   = address;
        this.latitude  = latitude;
        this.longitude = longitude;
    }
}
