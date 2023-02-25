package software.pera.roplanify.controller;

import com.google.maps.errors.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.pera.roplanify.model.dto.PlaceResponse;
import software.pera.roplanify.service.PlaceService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/place")
@AllArgsConstructor
@Validated
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/search")
    public ResponseEntity<List<PlaceResponse>> search(@Valid @NotNull @RequestParam String input) throws IOException, InterruptedException, ApiException {
        List<PlaceResponse> response = placeService.search(input);
        return ResponseEntity.ok(response);
    }

}
