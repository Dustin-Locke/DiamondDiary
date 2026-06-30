package locke.dustin.diamonddiary.repository;

import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.entity.join.UserPlayer;
import locke.dustin.diamonddiary.util.embedded_id.UserPlayerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPlayerRepository
        extends JpaRepository< UserPlayer, UserPlayerId > {

    UserPlayer findByUserAndPlayer (
            User user,
            Player player );

    Boolean existsByUser_IdAndPlayer_Id (
            UUID userId,
            UUID playerId );

}
