package locke.dustin.diamonddiary.dto.player;

import jakarta.validation.constraints.*;
import locke.dustin.diamonddiary.type.FieldingPosition;

import java.time.LocalDate;

public record CreatePlayerRequest(
        @NotBlank(message = "The player's first name is required." )
        String firstName,
        @NotBlank(message = "The player's last name is required." )
        String lastName,
        @NotBlank(message = "The player's birth date is required." )
        LocalDate birthDate,
        @PositiveOrZero
        @Size(max = 99 )
        int preferredJerseyNumber,
        FieldingPosition primaryPosition,
        FieldingPosition secondaryPosition
) {
}
