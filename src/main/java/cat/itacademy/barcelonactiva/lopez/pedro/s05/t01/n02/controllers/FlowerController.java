package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services.FlowerServiceInterface;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers.util.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flowers")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FlowerController {

    @Autowired
    private final FlowerServiceInterface flowerService;

    @Operation(summary = "It creates a new flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the flower successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "406", description = "Flower values invalid.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "There is an error on the creation request form. Please check the body.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))})
    })
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createFlower(@Valid @RequestBody FlowerDTO flower, WebRequest webRequest) {
        flowerService.createFlower(flower);
        return new ResponseEntity<>(new Message(HttpStatus.OK.value(), "Flower has been created", webRequest.getDescription(false), new Date()), HttpStatus.OK);
    }

    @Operation(summary = "It updates a flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the flower successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied. Please, also double-checking the request body is suggested.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))})
    })
    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateFlower(@PathVariable int id, @Valid @RequestBody FlowerDTO flowerDTO, WebRequest webRequest) {
        try {
            flowerService.updateFlower(flowerDTO, id);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), " Flower has been updated", webRequest.getDescription(false), new Date()), HttpStatus.OK);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", webRequest.getDescription(false), new Date()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "It deletes a flower.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the flower successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))})
    })
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteFlower(@PathVariable int id, WebRequest webRequest) {
        try {
            flowerService.deleteFlowerBy(id);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), " Flower has been deleted", webRequest.getDescription(false), new Date()), HttpStatus.ACCEPTED);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", webRequest.getDescription(false), new Date()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "It gets a flower by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the flower successfully.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))})
    })
    @GetMapping(value = "/getOne/{id}", produces = "application/json")
    public ResponseEntity<?> getFlowerBy(@PathVariable int id) {
        try {
            return new ResponseEntity<>(flowerService.getFlowerBy(id), HttpStatus.OK);
        } catch (FlowerDoesNotExistException e) {
            return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST.value(), "Flower does not exist", "The flower does is not on the database", new Date()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "It gets all the flowers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower list successfully.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Message.class))})
    })
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFlowers() {
        List<FlowerDTO> flowers = flowerService.getFlowers();
        if (flowers.isEmpty()) {
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), "The Flower database seems to be empty.", "There is any record on the flower database", new Date()), HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.ok(flowerService.getFlowers());
        }
    }
}
