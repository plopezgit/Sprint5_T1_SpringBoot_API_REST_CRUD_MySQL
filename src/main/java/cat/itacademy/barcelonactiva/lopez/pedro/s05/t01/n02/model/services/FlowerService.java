package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService implements FlowerServiceInterface{

    @Autowired
    FlowerRepository flowersRepository;


    @Override
    public void createFlower(FlowerDTO flowerDTO) {

    }

    @Override
    public void updateFlower(FlowerDTO flowerDTO) {

    }

    @Override
    public List<FlowerDTO> flowers() {
        return null;
    }

    @Override
    public FlowerDTO getOneFlowerBy(int id) {
        return null;
    }

    @Override
    public FlowerDTO deleteFlowerBy(int id) {
        return null;
    }


}
