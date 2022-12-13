package software.pera.roplanify.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodedWaypoint;
import com.google.maps.model.PlaceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.pera.roplanify.model.OptimizeRouteRequest;
import software.pera.roplanify.model.OptimizeRouteResponse;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RouteService {

    @Value("${google.cloud.api-key}")
    String googleCloudApiKey;

    public OptimizeRouteResponse optimizeRoute(OptimizeRouteRequest request) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleCloudApiKey)
                .build();

        DirectionsResult directionsResult = DirectionsApi.newRequest(context)
                .mode(request.getTravelMode())
                .origin(request.getOrigin())
                .waypoints(request.getWaypoints())
                .destination(request.getDestination())
                .optimizeWaypoints(true)
                .await();

        OptimizeRouteResponse response = new OptimizeRouteResponse();

        for (GeocodedWaypoint geocodedWaypoint : directionsResult.geocodedWaypoints) {
            PlaceDetails placeDetails = PlacesApi.placeDetails(context, geocodedWaypoint.placeId).await();
            response.getOptimizedWaypoints().add(placeDetails.geometry.location);
        }

        return response;
    }

}
