package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlowerServiceInterface {

    void createFlower(FlowerDTO flower);

    void updateFlower(FlowerDTO flower, int id) throws FlowerDoesNotExistException;

    List<FlowerDTO> getFlowers();

    FlowerDTO getFlowerBy(int id) throws FlowerDoesNotExistException;

    void deleteFlowerBy(int id) throws FlowerDoesNotExistException;


}
