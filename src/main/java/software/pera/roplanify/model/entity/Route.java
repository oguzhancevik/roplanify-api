package software.pera.roplanify.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static software.pera.roplanify.util.Constant.SYSTEM.ZONE_ID;

@Getter
@Setter
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq_gen")
    @SequenceGenerator(name = "route_seq_gen", sequenceName = "route_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(ZONE_ID);

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Place> places;

}
