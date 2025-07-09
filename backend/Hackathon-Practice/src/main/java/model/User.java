package model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Table name supposedly
@Data // generates getters, setters, toString, equals, and hashcode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
}