package locke.dustin.diamonddiary.dto.geolocation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeoLocationRequest(
        @NotBlank(message = "A location name is required." )
        String name,

        @Valid @NotNull
        AddressRequest address
) { }