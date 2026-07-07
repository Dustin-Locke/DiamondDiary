package locke.dustin.diamonddiary.test_data;

import locke.dustin.diamonddiary.dto.geolocation.*;
import locke.dustin.diamonddiary.dto.player.CreatePlayerRequest;
import locke.dustin.diamonddiary.entity.*;
import locke.dustin.diamonddiary.entity.stats.*;
import locke.dustin.diamonddiary.type.ClassDesignation;
import locke.dustin.diamonddiary.type.FieldingPosition;
import locke.dustin.diamonddiary.util.*;

import java.time.LocalDate;
import java.util.UUID;


public class GenerateTestEntity {

    public User testUser ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174000" );

        User user = new User( );
        user.setId( id );
        user.setEmail( "test@email.com" );
        user.setPasswordHash( "hashedPassword" );

        return user;
    }

    public Player testPlayer ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174001" );

        Player player = new Player( );
        player.setId( id );
        player.setFirstName( "Sam" );
        player.setLastName( "Reich" );
        player.setBirthDate( LocalDate.now( ) );
        player.setPreferredJerseyNumber( 99 );
        player.setPrimaryPosition( FieldingPosition.CATCHER );
        player.setSecondaryPosition( FieldingPosition.CORNERS );

        return player;
    }

    public CreatePlayerRequest testPlayerRequest ( ) {

        CreatePlayerRequest request = new CreatePlayerRequest(
                "Sam",
                "Reich",
                LocalDate.now( ),
                99,
                FieldingPosition.CATCHER,
                FieldingPosition.CORNERS );

        System.out.println( request.toString( ) );

        return request;
    }

    public Team testTeam ( ) {

        UUID
                id =
                UUID.fromString( "123e4567-e89b-12d3-a456-426614174002" );
        GeoLocation practiceLocation = testGeolocation( );

        Team team = new Team( );
        team.setId( id );
        team.setName( "Volts" );
        team.setAgeDesignation( 14 );
        team.setClassDesignation( ClassDesignation.A );
        team.setPracticeLocation( practiceLocation );

        return team;
    }

    public Team testOpponent ( ) {

        UUID
                id =
                UUID.fromString( "123e4567-e89b-12d3-a456-426614174003" );
        GeoLocation practiceLocation = testGeolocation( );

        Team team = new Team( );
        team.setId( id );
        team.setName( "Blaze" );
        team.setAgeDesignation( 14 );
        team.setClassDesignation( ClassDesignation.A );
        team.setPracticeLocation( practiceLocation );

        return team;
    }

    public GeoLocation testGeolocation ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174005" );

        Address address = testAddress( );

        GeoLocation geoLocation = new GeoLocation( );
        geoLocation.setId( id );
        geoLocation.setName( "Ed Austin Park, Field 7" );
        geoLocation.setAddress( address );

        return geoLocation;
    }

    public GeoLocationRequest testGeoLocationRequest ( ) {

        AddressRequest address = testAddressRequest( );

        return new GeoLocationRequest(
                "Ed Austin Park, Field 7",
                address );
    }

    public GeoLocationResponse testGeoLocationResponse ( ) {

        AddressResponse addressResponse = testAddressResponse( );
        Coordinates     coordinates     = testCoordinates( );

        return new GeoLocationResponse(
                UUID.fromString( "123e4567-e89b-12d3-a456-426614174005" ),
                "Ed Austin Park, Field 7",
                addressResponse,
                coordinates.latitude( ),
                coordinates.longitude( ) );
    }

    public GeoLocationRequest testUpdateGeoLocationNameRequest ( ) {

        AddressRequest addressRequest = testAddressRequest( );

        return new GeoLocationRequest(
                "Ed Austin Park, Softball Field 8",
                addressRequest );
    }

    public GeoLocationResponse testUpdatedGeoLocationNameResponse ( ) {

        AddressResponse addressResponse = testAddressResponse( );
        Coordinates     coordinates     = testCoordinates( );

        return new GeoLocationResponse(
                UUID.fromString( "123e4567-e89b-12d3-a456-426614174005" ),
                "Ed Austin Park, Softball Field 8",
                addressResponse,
                coordinates.latitude( ),
                coordinates.longitude( ) );
    }

    public Coordinates testCoordinates ( ) {

        return new Coordinates(
                30.3646978,
                -81.5071858 );
    }

    public Address testAddress ( ) {

        Address address = new Address( );
        address.setStreetNumber( "11751" );
        address.setStreetName( "McCormick Rd" );
        address.setApartmentNumber( "" );
        address.setCity( "Jacksonville" );
        address.setState( "Florida" );
        address.setZip( "32225" );

        return address;
    }


    public AddressRequest testAddressRequest ( ) {

        return new AddressRequest(
                "11751",
                "McCormick Rd",
                "",
                "Jacksonville",
                "Florida",
                "32225" );
    }

    public AddressResponse testAddressResponse ( ) {

        return new AddressResponse(
                "11751",
                "McCormick Rd",
                "",
                "Jacksonville",
                "Florida",
                "32225" );
    }

    public GeoLocation testWrongGeoLocation ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174005" );

        Address     wrongAddress     = testWrongAddress( );
        Coordinates wrongCoordinates = testWrongCoordinates( );

        GeoLocation wrongGeoLocation = new GeoLocation( );
        wrongGeoLocation.setId( id );
        wrongGeoLocation.setName( "Ed Austin Park, Field 7" );
        wrongGeoLocation.setAddress( wrongAddress );
        wrongGeoLocation.setLatitude( wrongCoordinates.latitude( ) );
        wrongGeoLocation.setLongitude( wrongCoordinates.longitude( ) );

        return wrongGeoLocation;
    }

    public Address testWrongAddress ( ) {

        Address wrongAddress = new Address( );
        wrongAddress.setStreetNumber( "101" );
        wrongAddress.setStreetName( "Penman Rd S" );
        wrongAddress.setApartmentNumber( "" );
        wrongAddress.setCity( "Jacksonville Beach" );
        wrongAddress.setState( "Florida" );
        wrongAddress.setZip( "32250" );

        return wrongAddress;
    }

    public Coordinates testWrongCoordinates ( ) {

        return new Coordinates(
                30.27835,
                -81.39343 );
    }

    public Game testGame ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174004" );

        Game game = new Game( );
        game.setId( id );
        game.setTeam( testTeam( ) );
        game.setOpponent( testOpponent( ) );
        game.setLocation( testGeolocation( ) );
        game.setDate( LocalDate.now( ) );

        return game;
    }

    public BattingStats testBattingStats ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174006" );

        BattingStats battingStats = new BattingStats( );
        battingStats.setId( id );
        battingStats.setPlateAppearances( 3 );
        battingStats.setAtBats( 1 );
        battingStats.setHits( 1 );
        battingStats.setSingleBaseHits( 0 );
        battingStats.setDoubleBaseHits( 0 );
        battingStats.setTripleBaseHits( 0 );
        battingStats.setHomeRuns( 1 );
        battingStats.setRunnersBattedIn( 3 );
        battingStats.setBaseOnBalls( 1 );
        battingStats.setStrikeouts( 0 );
        battingStats.setStrikeoutsLooking( 0 );
        battingStats.setHitByPitch( 1 );
        battingStats.setSacrificeHitsAndBunts( 0 );
        battingStats.setReachesOnError( 0 );
        battingStats.setHitIntoFieldersChoice( 0 );
        battingStats.setStolenBases( 0 );
        battingStats.setCaughtStealing( 0 );
        battingStats.setPickedOff( 0 );

        return battingStats;
    }

    public FieldingStats testFieldingStats ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174007" );

        FieldingStats fieldingStats = new FieldingStats( );
        fieldingStats.setId( id );
        fieldingStats.setTotalChances( 5 );
        fieldingStats.setAssists( 1 );
        fieldingStats.setPutOuts( 4 );
        fieldingStats.setErrors( 0 );
        fieldingStats.setDoublePlays( 1 );
        fieldingStats.setTriplePlays( 0 );

        return fieldingStats;
    }

    public PitchingStats testPitchingStats ( ) {

        UUID id = UUID.fromString( "123e4567-e89b-12d3-a456-426614174008" );

        PitchingStats pitchingStats = new PitchingStats( );
        pitchingStats.setId( id );
        pitchingStats.setOutsRecorded( 18 );
        pitchingStats.setStartedGame( true );
        pitchingStats.setBattersFaced( 31 );
        pitchingStats.setTotalPitches( 120 );
        pitchingStats.setWinningPitcher( true );
        pitchingStats.setLosingPitcher( false );
        pitchingStats.setDidSaveWin( false );
        pitchingStats.setHadOpportunityToSave( false );
        pitchingStats.setBlownSave( false );
        pitchingStats.setHitsAllowed( 5 );
        pitchingStats.setRunsAllowed( 7 );
        pitchingStats.setEarnedRunsAllowed( 6 );
        pitchingStats.setBaseOnBalls( 4 );
        pitchingStats.setStrikeouts( 8 );
        pitchingStats.setStrikeoutsLooking( 5 );
        pitchingStats.setHitByPitch( 2 );
        pitchingStats.setRunnersLeftOnBase( 6 );
        pitchingStats.setBalks( 0 );
        pitchingStats.setRunnersPickedOff( 0 );
        pitchingStats.setRunnersCaughtStealing( 0 );
        pitchingStats.setStolenBasesAllowed( 0 );
        pitchingStats.setWildPitches( 0 );

        return pitchingStats;
    }
}
