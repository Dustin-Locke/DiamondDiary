package locke.dustin.diamonddiary.auth.component;

import locke.dustin.diamonddiary.auth.UserPrincipal;
import locke.dustin.diamonddiary.entity.User;

public class UserPrincipalFactory {

    public static UserPrincipal fromUser ( User user ) {

        return new UserPrincipal(
                user.getId( ),
                user.getEmail( ),
                user.getPasswordHash( )
        );
    }
}