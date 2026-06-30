package locke.dustin.diamonddiary.component;

import locke.dustin.diamonddiary.dto.user.*;

import locke.dustin.diamonddiary.entity.User;

public class UserMapper {

    public static User toEntity ( UserRequest request ) {

        User user = new User( );
        user.setEmail( request.email( ) );

        return user;
    }

    public static User toEntity ( CreateUserRequest request ) {

        User user = new User( );
        user.setEmail( request.email( ) );

        return user;
    }

    public static UserResponse toResponse ( User user ) {

        return new UserResponse(
                user.getId( ),
                user.getEmail( )
        );
    }

    public static UserRequest toRequest ( User user ) {

        return new UserRequest( user.getEmail( ) );
    }

    public static void updateEntity (
            User user,
            UserRequest request ) {

        user.setEmail( request.email( ) );
    }
}
