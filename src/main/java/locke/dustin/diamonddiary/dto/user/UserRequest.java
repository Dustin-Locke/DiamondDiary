package locke.dustin.diamonddiary.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank(message = "Email is required to login." )
        String email
) {
}
