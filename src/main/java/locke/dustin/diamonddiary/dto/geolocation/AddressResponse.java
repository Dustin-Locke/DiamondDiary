package locke.dustin.diamonddiary.dto.geolocation;

import java.util.UUID;

public record AddressResponse(
        String streetNumber,
        String streetName,
        String apartmentNumber,
        String city,
        String state,
        String zip

) {
}
