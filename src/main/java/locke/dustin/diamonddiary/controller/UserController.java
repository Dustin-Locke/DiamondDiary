package locke.dustin.diamonddiary.controller;

import jakarta.validation.Valid;
import locke.dustin.diamonddiary.auth.UserPrincipal;
import locke.dustin.diamonddiary.auth.dto.PasswordUpdateRequest;
import locke.dustin.diamonddiary.component.UserMapper;
import locke.dustin.diamonddiary.dto.join.CreateUserPlayerRequest;
import locke.dustin.diamonddiary.dto.join.UserPlayerResponse;
import locke.dustin.diamonddiary.dto.player.PlayerResponse;
import locke.dustin.diamonddiary.dto.user.*;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.auth.service.AuthService;
import locke.dustin.diamonddiary.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user" )
@RequiredArgsConstructor
public class UserController {

    private final UserService       userService;
    private final UserPlayerService userPlayerService;
    private final AuthService       authService;

    @GetMapping
    public ResponseEntity< List< UserResponse > > getAllUsers ( ) {

        List< User > users = userService.findAll( );
        List< UserResponse > userResponses = users
                .stream( )
                .map( UserMapper::toResponse )
                .collect( Collectors.toList( ) );

        return ResponseEntity
                .ok( userResponses );
    }

    @PostMapping("/register" )
    public ResponseEntity< UserResponse > createUser (
            @RequestBody
            @Valid
            CreateUserRequest request ) {

        UserResponse savedUser = userService.create( request );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( savedUser );
    }

    @PutMapping("/me" )
    public ResponseEntity< UserResponse > updateUser (
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody UserRequest request ) {

        UserResponse
                updated =
                userService.update(
                        user.id( ),
                        request );

        return ResponseEntity
                .ok( updated );
    }

    @PutMapping("/me/password" )
    public ResponseEntity< String > updatePassword (
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody
            @Valid PasswordUpdateRequest request ) {

        authService.updatePassword(
                user.id( ),
                request );

        return ResponseEntity
                .ok( "Password updated successfully." );
    }

    @PostMapping("/me/add-player" )
    public ResponseEntity< UserPlayerResponse > addPlayerToUser (
            @AuthenticationPrincipal UserPrincipal user,
            @RequestParam UUID requestPlayerId ) {

        UUID userId = user.id( );

        CreateUserPlayerRequest createUnionRequest =
                new CreateUserPlayerRequest(
                        userId,
                        requestPlayerId );

        UserPlayerResponse userPlayerResponse =
                userPlayerService.addPlayerToUser( createUnionRequest );

        return ResponseEntity
                .ok( userPlayerResponse );
    }

    @DeleteMapping("/{email}" )
    public ResponseEntity< Void > deleteUser (
            @PathVariable String email ) {

        User user = userService.findByEmail( email );
        userService.deleteById( user.getId( ) );

        return ResponseEntity
                .noContent( )
                .build( );
    }
}
