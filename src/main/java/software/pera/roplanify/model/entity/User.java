package software.pera.roplanify.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import software.pera.roplanify.model.type.LoginType;

import java.time.LocalDateTime;
import java.util.List;

import static software.pera.roplanify.util.Constant.SYSTEM.ZONE_ID;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(ZONE_ID);

    private String fullName;

    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String language;

    private boolean notificationAllowed;

    private boolean smsAllowed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Route> reports;

}
