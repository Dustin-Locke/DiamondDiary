package locke.dustin.diamonddiary.dto.join;

import lombok.NonNull;

import java.util.UUID;

public record CreateUserPlayerRequest(
        @NonNull
        UUID userId,
        @NonNull
        UUID playerId
) {
}
