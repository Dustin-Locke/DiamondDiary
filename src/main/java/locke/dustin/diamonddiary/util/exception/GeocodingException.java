package locke.dustin.diamonddiary.util.exception;

import org.springframework.web.client.RestClientException;

public class GeocodingException extends RuntimeException {

    public GeocodingException (
            String message,
            RestClientException exception ) {

        super( message +
               ": " +
               exception );
    }
}
