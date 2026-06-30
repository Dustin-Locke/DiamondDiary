package locke.dustin.diamonddiary.auth.dto;

import jakarta.validation.constraints.*;

// Respond with UserResponse
public record LoginRequest(
        @NotBlank(message = "An email address is required to login." )
        @Email(message = "A valid email address is required to login." )
        String email,

        @NotBlank(message = "The password must not be blank." )
        @Min(16 )
        String password
) {
}
