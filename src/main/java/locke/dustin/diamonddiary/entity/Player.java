package locke.dustin.diamonddiary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import locke.dustin.diamonddiary.type.FieldingPosition;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column(name = "first_name" )
    private String firstName;

    @NonNull
    @Column(
            name = "last_name",
            nullable = false )
    private String lastName;

    @NotNull
    @Column(
            name = "birth_date",
            nullable = false )
    private LocalDate birthDate;

    @PositiveOrZero
    @Size(max = 99 )
    @Column(name = "preferred_jersey_number" )
    private int preferredJerseyNumber;

    @Column(name = "primary_position" )
    private FieldingPosition primaryPosition;

    @Column(name = "secondary_position" )
    private FieldingPosition secondaryPosition;

    public String getName ( ) {

        return firstName + " " + lastName;
    }

}
