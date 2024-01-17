package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services.FlowerServiceInterface;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.util.ApiMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/flowers")
@RequiredArgsConstructor
public class FlowerController {

    @Autowired
    private final FlowerServiceInterface flowerService;

    @Operation(summary = "It creates a new flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the flower successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "There is an error on the creation request form. Please check the body.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found.",
                    content = @Content)
    })
    @PostMapping (value = "/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createFlower (@RequestBody FlowerDTO flower) throws ServerException {
        FlowerDTO flowerDTO = flowerService.createFlower(flower);
        try {
            if (flowerDTO == null) {
                throw new ServerException("There is server exception error, please try again later.");
            } else {
                return new ResponseEntity<>(new ApiMessage(HttpStatus.OK.value(), "Flower has been updated", new Date()), HttpStatus.ACCEPTED);
            }
        } catch (ServerException e) {
            return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), new Date()), HttpStatus.BAD_REQUEST);

        }
    }

    @Operation(summary = "It updates a flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the flower successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied. Please, also double-checking the request body is suggested.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = @Content)
    })
    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateFlower (@PathVariable int id, @RequestBody Flower flowerDTO) {
        FlowerDTO thisFlower = null;
        try {
            thisFlower = flowerService.getOneFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", new Date()), HttpStatus.BAD_REQUEST);
        }
        thisFlower.setName(flowerDTO.getName());
        thisFlower.setCountry(flowerDTO.getCountry());
        FlowerDTO updatedFlower = flowerService.createFlower(thisFlower);

        return new ResponseEntity<>(new ApiMessage(HttpStatus.OK.value(), updatedFlower + " Flower has been updated", new Date()), HttpStatus.ACCEPTED);

    }

    @Operation(summary = "It deletes a flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the flower successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = @Content)
    })
    @DeleteMapping (value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteFlower (@PathVariable int id) {
        try {
            flowerService.deleteFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", new Date()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiMessage(HttpStatus.OK.value(), " Flower has been deleted", new Date()), HttpStatus.ACCEPTED);

    }

    @Operation(summary = "It gets a flower by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the flower successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = @Content)
    })
    @GetMapping (value = "/getOne/{id}", produces = "application/json")
    public ResponseEntity<?> getOneFlowerByID (@PathVariable int id) {
        FlowerDTO thisFlower;
        try {
            thisFlower = flowerService.getOneFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", new Date()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiMessage(HttpStatus.OK.value(), thisFlower + " Here comes the Flower", new Date()), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "It gets all the flowers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower list successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = @Content)
    })
    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers () {
        return ResponseEntity.ok(flowerService.getFlowers());
    }

}
