package software.pera.roplanify.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_seq_gen")
    @SequenceGenerator(name = "place_seq_gen", sequenceName = "place_seq", allocationSize = 1)
    private Long id;

    private String googlePlaceId;

    private String name;

    private String formattedAddress;

    private double lat;

    private double lang;

    private byte routeIndex;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

}
