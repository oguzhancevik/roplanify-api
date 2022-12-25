package software.pera.roplanify.service;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.pera.roplanify.model.PlaceResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    @Value("${google.cloud.api-key}")
    String googleCloudApiKey;

    public List<PlaceResponse> search(String input) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleCloudApiKey)
                .build();

        AutocompletePrediction[] autocompletePredictionResponse = PlacesApi.placeAutocomplete(context, input, null).await();

        List<PlaceResponse> response = new ArrayList<>();

        for (AutocompletePrediction autocompletePrediction : autocompletePredictionResponse) {
            PlaceDetails placeDetails = PlacesApi.placeDetails(context, autocompletePrediction.placeId).await();
            PlaceResponse place = new PlaceResponse(placeDetails.placeId, placeDetails.name, placeDetails.geometry.location);
            response.add(place);
        }

        return response;
    }

}
