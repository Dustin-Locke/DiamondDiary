package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.util.exception.CoordinatesNotValidException;
import org.springframework.stereotype.Service;

@Service
public class MapLinkGenerator {

    public String generateGoogleMapsLink (
            Double latitude,
            Double longitude ) {

        validateCoordinates( latitude, longitude );

        return "https://www.google.com/maps/search/?api=1&query="
               + latitude
               + ","
               + longitude;
    }

    public String generateAppleMapsLink (
            Double latitude,
            Double longitude ) {

        validateCoordinates( latitude, longitude );

        return "http://maps.apple.com/?ll="
               + latitude
               + ","
               + longitude;
    }

    public String generateWazeMapsLink (
            Double latitude,
            Double longitude ) {

        validateCoordinates( latitude, longitude );

        return "https://www.waze.com/ul?ll="
               + latitude
               + "%2C"
               + longitude;
    }

    private void validateCoordinates ( Double latitude,
                                       Double longitude ) {
        if (latitude == null || longitude == null) {
            throw new CoordinatesNotValidException (
                    "Latitude and longitude are required."
            );
        }

        if (latitude < -90 || latitude > 90) {
            throw new CoordinatesNotValidException(
                    "Latitude must be between -90 and 90."
            );
        }

        if (longitude < -180 || longitude > 180) {
            throw new CoordinatesNotValidException(
                    "Longitude must be between -180 and 180."
            );
        }
    }
}
