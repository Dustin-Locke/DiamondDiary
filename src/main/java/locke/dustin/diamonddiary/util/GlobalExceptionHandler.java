package locke.dustin.diamonddiary.util;

import jakarta.persistence.EntityNotFoundException;
import locke.dustin.diamonddiary.dto.ErrorResponse;
import locke.dustin.diamonddiary.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger( GlobalExceptionHandler.class );

    private ErrorResponse createErrorResponse ( Exception exception ) {

        return new ErrorResponse(
                exception.getMessage( ),
                LocalDateTime.now( ) );
    }

    @ExceptionHandler(EntityNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleNotFound (
            EntityNotFoundException exception ) {

        logger.info(
                "Entity not found: {}",
                exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(IllegalArgumentException.class )
    public ResponseEntity< ErrorResponse > handleBadRequest (
            IllegalArgumentException exception ) {

        logger.warn(
                "Bad request: {}",
                exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                             .body( error );
    }


    @ExceptionHandler(UserNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleUserNotFoundException (
            UserNotFoundException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(LocationNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleLocationNotFound (
            LocationNotFoundException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(SamePasswordException.class )
    public ResponseEntity< ErrorResponse > handleSamePasswordException (
            SamePasswordException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.CONFLICT )
                             .body( error );
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class )
    public ResponseEntity< ErrorResponse > handlePlayerAlreadyExistsException (
            PlayerAlreadyExistsException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.CONFLICT )
                             .body( error );
    }

    @ExceptionHandler(PlayerNotFoundException.class )
    public ResponseEntity< ErrorResponse > handlePlayerNotFound (
            PlayerNotFoundException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(UserPlayerAlreadyExistsException.class )
    public ResponseEntity< ErrorResponse > handleUserPlayerAlreadyExistsException (
            UserPlayerAlreadyExistsException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.CONFLICT )
                             .body( error );
    }

    @ExceptionHandler(UserPlayerNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleUserPlayerNotFound (
            UserPlayerNotFoundException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(CoordinatesNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCoordinates (
            CoordinatesNotValidException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                             .body( error );
    }

    @ExceptionHandler(AddressNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleAddressNotFound (
            AddressNotFoundException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( error );
    }

    @ExceptionHandler(LocationAlreadyExistsException.class )
    public ResponseEntity< ErrorResponse > handleGeoLocationAlreadyExistsException (
            LocationAlreadyExistsException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.CONFLICT )
                             .body( error );
    }

    @ExceptionHandler(GeocodingException.class)
    public ResponseEntity<ErrorResponse> handleGeoCodingException (
            GeocodingException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                             .body( error );
    }

    @ExceptionHandler(UserAlreadyExistsException.class )
    public ResponseEntity< ErrorResponse > handleUserAlreadyExistsException (
            UserAlreadyExistsException exception ) {

        logger.info( exception.getMessage( ) );

        ErrorResponse error = createErrorResponse( exception );

        return ResponseEntity.status( HttpStatus.CONFLICT )
                             .body( error );
    }

}
