package locke.dustin.diamonddiary.util;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

    @NotBlank(message = "A street number is required." )
    private String streetNumber;

    @NotBlank(message = "A street name is required." )
    private String streetName;

    private String apartmentNumber;

    @NotBlank(message = "A city is required." )
    private String city;

    @NotBlank(message = "A state is required." )
    private String state;

    @NotBlank(message = "A zip code is required." )
    private String zip;

    public String toSingleLine ( ) {

        return String.join(
                ", ",
                streetNumber + " " + streetName,
                city,
                state,
                zip
                          );
    }

    @Override
    public String toString ( ) {

        if ( apartmentNumber != null && !apartmentNumber.isBlank( ) ) {
            return streetNumber + " " + streetName + "\n" +
                   "Apt. " + apartmentNumber + "\n" +
                   city + ", " + state + " " + zip;
        } else {
            return streetNumber + " " + streetName + "\n" +
                   city + ", " + state + " " + zip;
        }
    }
}
