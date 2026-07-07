package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.dto.user.CreateUserRequest;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException ( CreateUserRequest request ) {

        super( " User with email " +
               request.email() +
               ") already exists." );
    }
}
