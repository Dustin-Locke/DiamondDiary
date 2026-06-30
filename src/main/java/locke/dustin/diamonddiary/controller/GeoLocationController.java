package locke.dustin.diamonddiary.controller;

import jakarta.validation.Valid;
import locke.dustin.diamonddiary.dto.geolocation.GeoLocationRequest;
import locke.dustin.diamonddiary.dto.geolocation.GeoLocationResponse;
import locke.dustin.diamonddiary.service.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/location" )
@RequiredArgsConstructor
public class GeoLocationController {

    private final GeoLocationService geoLocationService;

    @PostMapping
    ResponseEntity< GeoLocationResponse > createGeoLocation (
            @Valid
            @RequestBody
            GeoLocationRequest geoLocationRequest
                                                            ) {

        return ResponseEntity.ok( geoLocationService.create(
                geoLocationRequest,
                geoLocationRequest.address( ) ) );
    }

    @DeleteMapping("/{id}" )
    public ResponseEntity< Void > deleteGeoLocation (
            @PathVariable UUID id ) {

        geoLocationService.deleteById( id );
        return ResponseEntity.noContent( ).build( );
    }

    @GetMapping("/search" )
    public ResponseEntity< List< GeoLocationResponse > > searchByTerm (
            @RequestParam String searchTerm ) {

        return ResponseEntity.ok( geoLocationService.findBySearchTerm( searchTerm ) );
    }

    @PutMapping("/{id}" )
    public ResponseEntity< GeoLocationResponse > updateGeoLocation (
            @PathVariable UUID id,
            @RequestBody GeoLocationRequest request ) {

        return ResponseEntity.ok( geoLocationService.update(
                id,
                request ) );
    }

}
