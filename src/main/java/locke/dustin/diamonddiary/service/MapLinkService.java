package locke.dustin.diamonddiary.service;

import org.springframework.stereotype.Service;

@Service
public class MapLinkService {

    public String generateGoogleMapsLink (
            Double latitude,
            Double longitude ) {

        return "https://www.google.com/maps/search/?api=1&query="
               + latitude
               + ","
               + longitude;
    }

    public String generateAppleMapsLink (
            Double latitude,
            Double longitude ) {

        return "http://maps.apple.com/?ll="
               + latitude
               + ","
               + longitude;
    }

    public String generateWazeMapsLink (
            Double latitude,
            Double longitude ) {

        return "https://www.waze.com/ul?ll="
               + latitude
               + "%2C"
               + longitude;
    }
}
