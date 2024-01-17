package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services.FlowerServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerServiceInterface flowerService;

    @Operation(summary = "It creates a new flower on database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the book successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<FlowerDTO> createFlower (@RequestBody FlowerDTO flower ) throws ServerException {
        FlowerDTO flowerDTO = flowerService.createFlower(flower);
        if (flowerDTO == null) {
            throw new ServerException("There is server exception error, please try again later.");
        } else {
            return new ResponseEntity<>(flowerDTO, HttpStatus.CREATED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<FlowerDTO> updateFlower (@PathVariable int id, @RequestBody Flower flowerDTO) {
        FlowerDTO thisFlower = null;
        try {
            thisFlower = flowerService.getOneFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        thisFlower.setName(flowerDTO.getName());
        thisFlower.setCountry(flowerDTO.getCountry());
        FlowerDTO updatedFlower = flowerService.createFlower(thisFlower);

        return new ResponseEntity<>(updatedFlower, HttpStatus.ACCEPTED);

    }

    @DeleteMapping ("{id}")
    public ResponseEntity<HashMap<String, Boolean>> deleteFlower (@PathVariable int id) {
        try {
            flowerService.deleteFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Boolean> flowerDeletedState = new HashMap<>();
        flowerDeletedState.put("Deleted", true);

        return ResponseEntity.ok(flowerDeletedState);

    }

    @GetMapping ("{id}")
    public ResponseEntity<FlowerDTO> getOneFlowerByID (@PathVariable int id) {
        FlowerDTO thisFlower = null;
        try {
            thisFlower = flowerService.getOneFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(thisFlower);
    }

    @GetMapping
    public ResponseEntity<List<FlowerDTO>> getAllFlowers () {
        return ResponseEntity.ok(flowerService.getFlowers());
    }

}
