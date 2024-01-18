package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlowerService implements FlowerServiceInterface {

    private final FlowerRepository flowersRepository;

    @Autowired
    private ModelMapper flowerModelMapper;

    @Override
    public void createFlower(FlowerDTO flowerDTO) {
        flowersRepository.save(getBranchEntityFrom(flowerDTO));
    }

    @Override
    public void updateFlower(FlowerDTO flowerDTO, int id) throws FlowerDoesNotExistException {
        FlowerDTO flower = getOneFlowerBy(id);
        flower.setName(flowerDTO.getName());
        flower.setCountry(flowerDTO.getCountry());
        flowersRepository.save(getBranchEntityFrom(flower));
    }

    @Override
    public List<FlowerDTO> getFlowers() {
        List<Flower> flowers = flowersRepository.findAll();
        return flowers.stream().map(this::getBranchDTOFrom).collect(Collectors.toList());
    }

    @Override
    public FlowerDTO getOneFlowerBy(int id) throws FlowerDoesNotExistException {
        return flowersRepository.findById(id).map(this::getBranchDTOFrom)
                .orElseThrow(() -> new FlowerDoesNotExistException("The flower does not exist"));
    }

    @Override
    public void deleteFlowerBy(int id) throws FlowerDoesNotExistException {
        FlowerDTO branchDTO = getOneFlowerBy(id);
        flowersRepository.delete(getBranchEntityFrom(branchDTO));
    }

    private FlowerDTO getBranchDTOFrom(Flower flower) {
        return flowerModelMapper.map(flower, FlowerDTO.class);
    }

    private Flower getBranchEntityFrom(FlowerDTO flowerDTO) {
        return flowerModelMapper.map(flowerDTO, Flower.class);
    }

}
