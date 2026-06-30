package locke.dustin.diamonddiary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Email(message = "Invalid email format." )
    @NotBlank(message = "Email is required." )
    @Column(
            nullable = false,
            unique = true )
    private String email;

    @Column(nullable = false )
    private String passwordHash;

    public User ( String email ) {

        this.email = email;
    }

}
