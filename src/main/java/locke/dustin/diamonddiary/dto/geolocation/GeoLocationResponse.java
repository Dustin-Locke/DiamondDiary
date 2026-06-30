package locke.dustin.diamonddiary.dto.geolocation;

import java.util.UUID;

public record GeoLocationResponse(
        UUID id,
        String name,
        AddressResponse address,
        Double latitude,
        Double longitude
) {
}
