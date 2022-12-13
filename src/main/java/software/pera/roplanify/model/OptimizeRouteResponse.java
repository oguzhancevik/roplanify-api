package software.pera.roplanify.model;

import com.google.maps.model.LatLng;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OptimizeRouteResponse {

    List<LatLng> optimizedWaypoints = new ArrayList<>();

}
