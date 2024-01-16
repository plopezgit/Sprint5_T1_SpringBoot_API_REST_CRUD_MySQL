package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services.FlowerServiceInterface;
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

    @PostMapping
    public ResponseEntity<FlowerDTO> createCoin (@RequestBody FlowerDTO flower ) throws ServerException {
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
    public ResponseEntity<HashMap<String, Boolean>> deleteCoin (@PathVariable int id) {
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
    public ResponseEntity<FlowerDTO> getOneCoinByID (@PathVariable int id) {
        FlowerDTO thisFlower = null;
        try {
            thisFlower = flowerService.getOneFlowerBy(id);
        } catch (FlowerDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(thisFlower);
    }

    @GetMapping
    public ResponseEntity<List<FlowerDTO>> getAllCoins () {
        return ResponseEntity.ok(flowerService.getFlowers());
    }

}
