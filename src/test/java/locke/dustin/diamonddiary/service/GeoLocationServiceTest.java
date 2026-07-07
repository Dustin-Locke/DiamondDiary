package locke.dustin.diamonddiary.service;


import locke.dustin.diamonddiary.component.AddressMapper;
import locke.dustin.diamonddiary.component.GeoLocationMapper;
import locke.dustin.diamonddiary.dto.geolocation.GeoLocationRequest;
import locke.dustin.diamonddiary.dto.geolocation.GeoLocationResponse;
import locke.dustin.diamonddiary.repository.GeoLocationRepository;
import locke.dustin.diamonddiary.test_data.GenerateTestEntity;
import locke.dustin.diamonddiary.util.*;
import locke.dustin.diamonddiary.util.exception.LocationNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class )
public class GeoLocationServiceTest {

    GenerateTestEntity generate = new GenerateTestEntity( );

    @Mock
    private GeoLocationRepository geoLocationRepository;

    @Mock
    private GeocodingService geocodingService;

    @Mock
    private MapLinkGenerator mapLinkGenerator;

    @Mock
    private GeoLocationMapper geoLocationMapper;

    @InjectMocks
    private GeoLocationService geoLocationService;

    @Test
    void shouldCreateGeolocationSuccessfully ( ) {

        UUID               id      = UUID.randomUUID( );
        GeoLocationRequest request = generate.testGeoLocationRequest( );
        Address address =
                AddressMapper.toEntity( request.address( ) );
        Coordinates coordinates = generate.testCoordinates( );

        GeoLocation savedGeoLocation = generate.testGeolocation( );
        savedGeoLocation.setId( id );
        savedGeoLocation.setLatitude( coordinates.latitude( ) );
        savedGeoLocation.setLongitude( coordinates.longitude( ) );
        GeoLocationResponse expectedResponse =
                generate.testGeoLocationResponse( );

        when( geocodingService.getCoordinates( address ) ).thenReturn( coordinates );
        when( geoLocationMapper.toEntity(
                request,
                request.address( ) ) )
                .thenReturn( savedGeoLocation );
        when( geoLocationRepository
                      .findByNameAndAddress(
                              request.name( ),
                              address ) )
                .thenReturn( Optional.empty( ) );
        when( geoLocationRepository.save( any( GeoLocation.class ) ) )
                .thenReturn( savedGeoLocation );
        when( geoLocationMapper.toResponse( any( GeoLocation.class ) ) )
                .thenReturn( expectedResponse );

        GeoLocationResponse response = geoLocationService.create( request );

        assertAll(
                ( ) -> assertEquals(
                        expectedResponse.name( ),
                        response.name( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).streetNumber( ),
                        response.address( ).streetNumber( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).streetName( ),
                        response.address( ).streetName( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).apartmentNumber( ),
                        response.address( ).apartmentNumber( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).city( ),
                        response.address( ).city( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).state( ),
                        response.address( ).state( ) ),
                ( ) -> assertEquals(
                        expectedResponse.address( ).zip( ),
                        response.address( ).zip( ) ),
                ( ) -> assertEquals(
                        coordinates.latitude( ),
                        response.latitude( ) ),
                ( ) -> assertEquals(
                        coordinates.longitude( ),
                        response.longitude( ) )
                 );

        verify( geoLocationRepository ).findByNameAndAddress(
                request.name( ),
                address );
        verify( geocodingService ).getCoordinates( address );
        verify( geoLocationRepository ).save( any( GeoLocation.class ) );
    }

    @Test
    void shouldReturnGeoLocationSuccessfully ( ) {

        GeoLocationRequest
                geoLocationRequest =
                generate.testGeoLocationRequest( );
        String requestName = geoLocationRequest.name( );
        String
                requestStreetNumber =
                geoLocationRequest.address( ).streetNumber( );
        String
                requestStreetName =
                geoLocationRequest.address( ).streetName( );

        GeoLocation geoLocationEntity = generate.testGeolocation( );
        GeoLocationResponse
                expectedResponse =
                generate.testGeoLocationResponse( );

        when( geoLocationRepository
                      .findByNameAndAddress_StreetNumberAndAddress_StreetName(
                              requestName,
                              requestStreetNumber,
                              requestStreetName ) )
                .thenReturn( Optional.of( geoLocationEntity ) );
        when( geoLocationMapper.toResponse( geoLocationEntity ) ).thenReturn( expectedResponse );

        GeoLocationResponse
                response =
                geoLocationService.findByNameAndStreetNumberAndStreetName(
                        requestName,
                        requestStreetNumber,
                        requestStreetName );

        assertEquals(
                expectedResponse,
                response );

        verify( geoLocationRepository ).findByNameAndAddress_StreetNumberAndAddress_StreetName(
                requestName,
                requestStreetNumber,
                requestStreetName );
        verify( geoLocationMapper ).toResponse( geoLocationEntity );
    }

    @Test
    void shouldThrowExceptionWhenGeoLocationDoesNotExist ( ) {

        GeoLocationRequest
                geoLocationRequest =
                generate.testGeoLocationRequest( );
        String requestName = geoLocationRequest.name( );
        String
                requestStreetNumber =
                geoLocationRequest.address( ).streetNumber( );
        String
                requestStreetName =
                geoLocationRequest.address( ).streetName( );

        when( geoLocationRepository.findByNameAndAddress_StreetNumberAndAddress_StreetName(
                requestName,
                requestStreetNumber,
                requestStreetName ) )
                .thenReturn( Optional.empty( ) );

        assertThrows(
                LocationNotFoundException.class,
                ( ) -> geoLocationService.findByNameAndStreetNumberAndStreetName(
                        requestName,
                        requestStreetNumber,
                        requestStreetName )
                    );

        verify( geoLocationRepository ).findByNameAndAddress_StreetNumberAndAddress_StreetName(
                requestName,
                requestStreetNumber,
                requestStreetName );
        verifyNoInteractions( geoLocationMapper );
    }

    @Test
    void shouldFindAndUpdateGeoLocationNameSuccessfully ( ) {

        GeoLocationRequest
                updateGeoLocationRequest =
                generate.testUpdateGeoLocationNameRequest( );

        GeoLocation updatedGeoLocationEntity = generate.testGeolocation( );
        updatedGeoLocationEntity.setName( "Ed Austin Park, Softball Field 8" );

        GeoLocation geoLocationEntity = generate.testGeolocation( );
        UUID        geoLocationUUID   = geoLocationEntity.getId( );

        GeoLocationResponse
                expectedUpdatedGeoLocationResponse =
                generate.testUpdatedGeoLocationNameResponse( );


        when( geoLocationRepository.findById( geoLocationUUID ) ).thenReturn( Optional.of(  geoLocationEntity ) );
        when( geoLocationRepository.save( any( GeoLocation.class ) ) )
                .thenReturn( geoLocationEntity );
        when( geoLocationMapper.toResponse( any( GeoLocation.class ) ) )
                .thenReturn( expectedUpdatedGeoLocationResponse );

        GeoLocationResponse geoLocationResponse = geoLocationService.update(
                geoLocationUUID,
                updateGeoLocationRequest );

        assertEquals(
                geoLocationResponse,
                expectedUpdatedGeoLocationResponse );

        verify( geoLocationRepository ).findById( geoLocationUUID );
        verify( geoLocationMapper ).toResponse( geoLocationEntity );
        verifyNoInteractions( geocodingService );
    }

    @Test
    void shouldFindAndUpdateGeoLocationAddressSuccessfully ( ) {

        GeoLocationRequest
                updateGeoLocationRequest =
                generate.testGeoLocationRequest( );
        Address
                updatedAddress =
                AddressMapper.toEntity( updateGeoLocationRequest.address( ) );

        GeoLocation updatedGeoLocationEntity = generate.testGeolocation();

        Coordinates updatedCoordinates = generate.testCoordinates( );
        updatedGeoLocationEntity.setLatitude( updatedCoordinates.latitude( ) );
        updatedGeoLocationEntity.setLongitude( updatedCoordinates.longitude( ) );

        GeoLocationResponse
                expectedGeoLocationResponse =
                generate.testGeoLocationResponse( );

        GeoLocation geoLocationDatabase = generate.testWrongGeoLocation( );
        UUID        geoLocationUUID     = geoLocationDatabase.getId( );

        when( geoLocationRepository.findById( geoLocationUUID ) ).thenReturn( Optional.of( geoLocationDatabase ) );
        when( geocodingService.getCoordinates( updatedAddress ) ).thenReturn( updatedCoordinates );
        when( geoLocationRepository.save( any( GeoLocation.class ) ) )
                .thenReturn( updatedGeoLocationEntity );
        when( geoLocationMapper.toResponse( any( GeoLocation.class ) ) )
                .thenReturn( expectedGeoLocationResponse );

        GeoLocationResponse response = geoLocationService.update( geoLocationUUID,
                                                                  updateGeoLocationRequest);

        assertAll(
                () -> assertEquals(expectedGeoLocationResponse, response),
                () -> assertEquals(updateGeoLocationRequest.name(), geoLocationDatabase.getName()),
                () -> assertEquals(updatedAddress, geoLocationDatabase.getAddress()),
                () -> assertEquals(updatedCoordinates.latitude(), geoLocationDatabase.getLatitude()),
                () -> assertEquals(updatedCoordinates.longitude(), geoLocationDatabase.getLongitude())
        );

        verify( geoLocationRepository ).findById( geoLocationUUID );
        verify( geocodingService ).getCoordinates( updatedAddress );
        verify(geoLocationRepository).save(geoLocationDatabase);
        verify(geoLocationMapper).toResponse(geoLocationDatabase);

    }
}
