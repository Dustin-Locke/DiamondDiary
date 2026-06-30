package locke.dustin.diamonddiary.entity.join;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.Team;
import locke.dustin.diamonddiary.type.Season;
import locke.dustin.diamonddiary.util.embedded_id.PlayerTeamId;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "player_team" )
public class PlayerTeam {

    @NonNull
    @EmbeddedId
    private PlayerTeamId id;

    @NonNull
    @ManyToOne
    @MapsId("playerId" )
    @JoinColumn(
            name = "player_id",
            nullable = false )
    private Player player;

    @NonNull
    @ManyToOne
    @MapsId("teamId" )
    @JoinColumn(
            name = "team_id",
            nullable = false )
    private Team team;

    @PositiveOrZero
    @Min(
            value = 1995,
            message = "Team's season year cannot be before 1995." )
    @Max(
            value = 2100,
            message = "Team's season year cannot be past 2100." )
    private int year;

    @NonNull
    private Season season;

    @PositiveOrZero(message = "Player's jersey number cannot be negative." )
    @Max(
            value = 99,
            message = "Player's jersey number cannot be higher than 99." )
    @Column(name = "player_number" )
    private int playerNumber;

    @Positive(message = "Number of games played cannot be negative." )
    @Column(name = "games_played" )
    private int gamesPlayed;

    public PlayerTeam (
            @NonNull Player player,
            @NonNull Team team,
            int gamesPlayed ) {

        if ( player.getId( ) == null ) {
            throw new IllegalArgumentException(
                    "Player must be persisted before adding to a team." );
        }

        if ( team.getId( ) == null ) {
            throw new IllegalArgumentException(
                    "Team must be persisted before adding a player." );
        }

        this.id          = new PlayerTeamId(
                player.getId( ),
                team.getId( )
        );
        this.player      = player;
        this.team        = team;
        this.gamesPlayed = gamesPlayed;
    }

    public void incrementGamesPlayed ( ) {

        this.gamesPlayed += 1;
    }

    public void decrementGamesPlayed ( ) {

        this.gamesPlayed -= 1;
    }

    public void setYear ( int year ) {

        LocalDate today    = LocalDate.now( );
        int       nextYear = today.getYear( ) + 1;

        this.year =
                Math.min(
                        year,
                        nextYear );
    }
}
