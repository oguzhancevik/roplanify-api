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
import software.pera.roplanify.model.dto.OptimizeRouteRequest;
import software.pera.roplanify.model.dto.PlaceResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    @Value("${google.cloud.api-key}")
    String googleCloudApiKey;

    public List<PlaceResponse> optimizeRoute(OptimizeRouteRequest request) throws IOException, InterruptedException, ApiException {
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

        List<PlaceResponse> response = new ArrayList<>();

        for (GeocodedWaypoint geocodedWaypoint : directionsResult.geocodedWaypoints) {
            PlaceDetails placeDetails = PlacesApi.placeDetails(context, geocodedWaypoint.placeId).await();

            PlaceResponse place = PlaceResponse.builder()
                    .id(placeDetails.placeId)
                    .name(placeDetails.name)
                    .formattedAddress(placeDetails.formattedAddress)
                    .latLng(placeDetails.geometry.location)
                    .build();

            response.add(place);
        }

        return response;
    }

}
