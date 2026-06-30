package locke.dustin.diamonddiary.repository;

import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.type.FieldingPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface PlayerRepository extends JpaRepository< Player, UUID > {

    List< Player > findByLastNameContainingIgnoreCase ( String name );

    List< Player > findByPreferredJerseyNumber ( int jerseyNumber );

    List< Player > findByPrimaryPosition ( FieldingPosition position );

    List< Player > findBySecondaryPosition ( FieldingPosition position );

    Boolean existsByFirstNameAndLastNameAndBirthDate (
            String firstName,
            String lastName,
            LocalDate birthDate
                                                     );

    Optional< Player > findByFirstNameAndLastNameAndBirthDate (
            String firstName,
            String lastName,
            LocalDate birthDate
                                                              );
}
