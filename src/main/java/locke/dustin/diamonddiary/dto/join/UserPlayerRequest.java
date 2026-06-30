package locke.dustin.diamonddiary.dto.join;

import lombok.NonNull;

import java.util.UUID;

public record UserPlayerRequest(
        @NonNull
        UUID userId,
        @NonNull
        UUID playerId
) {
}
