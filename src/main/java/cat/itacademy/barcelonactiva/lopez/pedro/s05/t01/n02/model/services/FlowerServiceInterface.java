package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlowerServiceInterface {

    public void createFlower (FlowerDTO flower);
    public void updateFlower (FlowerDTO flower);
    public List<FlowerDTO> flowers ();
    public FlowerDTO getOneFlowerBy(int id);
    public FlowerDTO deleteFlowerBy(int id);


}
