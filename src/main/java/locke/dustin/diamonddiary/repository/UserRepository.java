package locke.dustin.diamonddiary.repository;

import locke.dustin.diamonddiary.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository< User, UUID > {

    User findByEmail ( String email );

    boolean existsByEmail ( String email );

    void deleteByEmail ( String email );

    void deleteById ( @NonNull UUID id );
}
