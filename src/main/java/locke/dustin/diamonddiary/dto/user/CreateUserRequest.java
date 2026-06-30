package locke.dustin.diamonddiary.dto.user;

import jakarta.validation.constraints.*;

// Respond with UserResponse
public record CreateUserRequest(

        @NotBlank(message = "Email is required to login." )
        @Email(message = "A valid email address is required." )
        String email
        ,
        @NotBlank(message = "Password is required to login." )
        @Min(
                value = 16,
                message = "The password must contain at least 16 characters." )
        String password
) {
}
