package locke.dustin.diamonddiary.util.embedded_id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerGameId implements Serializable {

    @NonNull
    @Column(
            name = "player_id",
            nullable = false )
    private UUID playerId;

    @NonNull
    @Column(
            name = "game_id",
            nullable = false )
    private UUID gameId;

}
