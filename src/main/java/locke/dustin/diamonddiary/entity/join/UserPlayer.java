package locke.dustin.diamonddiary.entity.join;

import jakarta.persistence.*;
import locke.dustin.diamonddiary.entity.*;
import locke.dustin.diamonddiary.util.embedded_id.UserPlayerId;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_player" )
public class UserPlayer {

    @NonNull
    @EmbeddedId
    private UserPlayerId id;

    @NonNull
    @ManyToOne
    @MapsId("userId" )
    @JoinColumn(
            name = "user_id",
            nullable = false )
    private User user;

    @NonNull
    @ManyToOne
    @MapsId("playerId" )
    @JoinColumn(
            name = "player_id",
            nullable = false )
    private Player player;

    public UserPlayer (
            User user,
            Player player ) {

        this.user = user;
        this.player = player;
    }
}
