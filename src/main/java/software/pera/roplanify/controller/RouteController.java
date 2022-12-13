package software.pera.roplanify.controller;

import com.google.maps.errors.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.pera.roplanify.model.OptimizeRouteRequest;
import software.pera.roplanify.model.OptimizeRouteResponse;
import software.pera.roplanify.service.RouteService;

import java.io.IOException;

@RestController
@RequestMapping("/api/route")
@AllArgsConstructor
@Validated
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/optimize")
    public ResponseEntity<OptimizeRouteResponse> optimizeRoute(@Valid @NotNull @RequestBody OptimizeRouteRequest request) throws IOException, InterruptedException, ApiException {
        OptimizeRouteResponse response = routeService.optimizeRoute(request);
        return ResponseEntity.ok(response);
    }

}
