package locke.dustin.diamonddiary.entity.join;

import jakarta.persistence.*;
import locke.dustin.diamonddiary.entity.Game;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.stats.*;
import locke.dustin.diamonddiary.util.embedded_id.PlayerGameId;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player_game" )
public class PlayerGame {

    @NonNull
    @EmbeddedId
    private PlayerGameId id;

    @NonNull
    @ManyToOne
    @MapsId("playerId" )
    @JoinColumn(name = "player_id" )
    private Player player;

    @NonNull
    @ManyToOne
    @MapsId("gameId" )
    @JoinColumn(
            name = "game_id",
            nullable = false )
    private Game game;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(
            name = "batting_stats",
            nullable = false )
    private BattingStats battingStats;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(
            name = "fielding_stats",
            nullable = false )
    private FieldingStats fieldingStats;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "pitching_stats" )
    private PitchingStats pitchingStats;
}
