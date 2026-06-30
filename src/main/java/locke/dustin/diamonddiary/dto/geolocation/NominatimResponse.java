package locke.dustin.diamonddiary.dto.geolocation;

public record NominatimResponse(
        String lat,
        String lon
) {

    @Override
    public String toString ( ) {

        return "Latitude: " + lat + "\n" + "Longitude: " + lon;
    }
}
