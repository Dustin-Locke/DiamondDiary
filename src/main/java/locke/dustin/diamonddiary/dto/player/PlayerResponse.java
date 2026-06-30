package locke.dustin.diamonddiary.dto.player;

import locke.dustin.diamonddiary.type.FieldingPosition;

import java.time.LocalDate;
import java.util.UUID;

public record PlayerResponse(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        int preferredJerseyNumber,
        FieldingPosition primaryPosition,
        FieldingPosition secondaryPosition
) {
}
