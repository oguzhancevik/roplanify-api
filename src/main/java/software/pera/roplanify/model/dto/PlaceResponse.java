package software.pera.roplanify.model.dto;

import com.google.maps.model.LatLng;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceResponse {

    String id;

    String name;

    String formattedAddress;

    LatLng latLng;

}
