package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerService implements FlowerServiceInterface {

    private final FlowerRepository flowersRepository;

    @Autowired
    public FlowerService (FlowerRepository flowersRepository){
        super();
        this.flowersRepository = flowersRepository;
    }

    @Autowired
    private ModelMapper flowerModelMapper;

    @Override
    public void createFlower(FlowerDTO flowerDTO) {
        flowersRepository.save(getFlowerEntityFrom(flowerDTO));
    }

    @Override
    public void updateFlower(FlowerDTO flowerDTO, int id) throws FlowerDoesNotExistException {
        FlowerDTO flower = getFlowerBy(id);
        flower.setName(flowerDTO.getName());
        flower.setCountry(flowerDTO.getCountry());
        flowersRepository.save(getFlowerEntityFrom(flower));
    }

    @Override
    public List<FlowerDTO> getFlowers() {
        List<Flower> flowers = flowersRepository.findAll();
        return flowers.stream().map(this::getFlowerDTOFrom).collect(Collectors.toList());
    }

    @Override
    public FlowerDTO getFlowerBy(int id) throws FlowerDoesNotExistException {
        return flowersRepository.findById(id).map(this::getFlowerDTOFrom)
                .orElseThrow(() -> new FlowerDoesNotExistException("The flower does not exist"));
    }

    @Override
    public void deleteFlowerBy(int id) throws FlowerDoesNotExistException {
        FlowerDTO branchDTO = getFlowerBy(id);
        flowersRepository.delete(getFlowerEntityFrom(branchDTO));
    }

    private FlowerDTO getFlowerDTOFrom(Flower flower) {
        return flowerModelMapper.map(flower, FlowerDTO.class);
    }

    private Flower getFlowerEntityFrom(FlowerDTO flowerDTO) {
        return flowerModelMapper.map(flowerDTO, Flower.class);
    }

}
