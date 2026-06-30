package locke.dustin.diamonddiary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import locke.dustin.diamonddiary.type.ClassDesignation;
import locke.dustin.diamonddiary.util.GeoLocation;
import lombok.*;

import java.util.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @NotBlank
    @Column(nullable = false )
    private String name;

    // Max age for players on team
    @NonNull
    @Positive
    @Min(5 )
    @Max(18 )
    @Column(
            name = "age_designation",
            nullable = false )
    private Integer ageDesignation;

    @Column(name = "class_designation" )
    private ClassDesignation classDesignation;

    @ManyToOne
    @JoinColumn(name = "practice_location" )
    private GeoLocation practiceLocation;

}
