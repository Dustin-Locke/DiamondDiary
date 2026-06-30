package locke.dustin.diamonddiary.entity.stats;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pitching_stats" )
public class PitchingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    // Used to calculate innings pitched.
    // Example: 11 outs recorded = 3.2 innings pitched
    @PositiveOrZero(message = "Outs recorded must be a positive integer." )
    @Column(name = "ip" )
    private int outsRecorded;

    @Column(name = "started_game" )
    private boolean startedGame;

    @PositiveOrZero(message = "Batters faced must be a positive integer." )
    @Column(name = "bf" )
    private int battersFaced;

    @PositiveOrZero(message = "Total pitches must be a positive integer." )
    @Column(name = "#p" )
    private int totalPitches;

    @Column(name = "w" )
    private boolean winningPitcher;

    @Column(name = "l" )
    private boolean losingPitcher;

    @Column(name = "sv" )
    private boolean didSaveWin;

    @Column(name = "svo" )
    private boolean hadOpportunityToSave;

    @Column(name = "bs" )
    private boolean blownSave;

    @PositiveOrZero(message = "Hits allowed must be a positive integer." )
    @Column(name = "h" )
    private int hitsAllowed;

    @PositiveOrZero(message = "Runs allowed must be a positive integer." )
    @Column(name = "r" )
    private int runsAllowed;

    @PositiveOrZero(message = "Earned runs allowed must be a positive integer" +
                              "." )
    @Column(name = "er" )
    private int earnedRunsAllowed;

    @PositiveOrZero(message = "Walks must be a positive integer." )
    @Column(name = "bb" )
    private int baseOnBalls;

    @PositiveOrZero(message = "Strikeouts must be a positive integer." )
    @Column(name = "so" )
    private int strikeouts;

    @PositiveOrZero(message = "Strikeouts looking must be a positive integer." )
    @Column(name = "kl" )
    private int strikeoutsLooking;

    @PositiveOrZero(message = "Hit by pitch must be a positive integer." )
    @Column(name = "hbp" )
    private int hitByPitch;

    @PositiveOrZero(message = "Runners left on base must be a positive " +
                              "integer." )
    @Column(name = "lob" )
    private int runnersLeftOnBase;

    @PositiveOrZero(message = "Balks must be a positive integer." )
    @Column(name = "bk" )
    private int balks;

    @PositiveOrZero(message = "Runners picked off must be a positive integer." )
    @Column(name = "pik" )
    private int runnersPickedOff;

    @PositiveOrZero(message = "Runners caught stealing must be a positive " +
                              "integer." )
    @Column(name = "cs" )
    private int runnersCaughtStealing;

    @PositiveOrZero(message = "Stolen bases allowed must be a positive " +
                              "integer." )
    @Column(name = "sb" )
    private int stolenBasesAllowed;

    @PositiveOrZero(message = "Wild pitches must be a positive integer." )
    @Column(name = "wp" )
    private int wildPitches;

}
