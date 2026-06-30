package locke.dustin.diamonddiary.dto.join;

import locke.dustin.diamonddiary.util.embedded_id.UserPlayerId;

import java.util.UUID;

public record UserPlayerResponse(
        UserPlayerId userPlayerId,
        UUID userId,
        UUID playerId
) {
}
