package locke.dustin.diamonddiary.dto.geolocation;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "A street number is required." )
        String streetNumber,

        @NotBlank(message = "A street name is required." )
        String streetName,

        String apartmentNumber,

        @NotBlank(message = "A city is required." )
        String city,

        @NotBlank(message = "A state is required." )
        String state,

        @NotBlank(message = "A zip code is required." )
        String zip
) {
}
