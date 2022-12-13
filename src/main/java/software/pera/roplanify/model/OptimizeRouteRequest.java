package software.pera.roplanify.model;

import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OptimizeRouteRequest {

    @NotNull
    TravelMode travelMode;

    @NotNull
    LatLng origin;

    @NotEmpty
    LatLng[] waypoints;

    @NotNull
    LatLng destination;

}
