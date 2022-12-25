package software.pera.roplanify.model;

import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceResponse {

    String id;

    String name;

    LatLng latLng;

}
