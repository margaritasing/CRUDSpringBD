package com.example.crudspringbd.Servicio;

import com.example.crudspringbd.Entidad.Animal;
import com.example.crudspringbd.Repositorio.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimales(){
        return animalRepository.findAll();
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> getAnimalByIdanimal(String idAnimal) {
        Integer id;
        try{
            id = Integer.parseInt(idAnimal);
        }catch (NumberFormatException e){
            id = 0;
        }
        return animalRepository.findAllById(List.of(id));
    }

    public void deleteById(Integer id) {
        animalRepository.deleteById(id);
    }

    public List<String> getColores() {
        return animalRepository.findDistinctByColor();
    }

    public List<Animal> findByNombreContaining(String nombre) {
        return animalRepository.findByNombreContaining(nombre);
    }

    public List<Animal> findByColor(String color) {
        return animalRepository.findByColor(color);
    }

    public List<Animal> findAnimalesByEdadBetween(Integer edadDesde, Integer edadHasta) {
        return animalRepository.findAnimalesByEdadBetween(edadDesde, edadHasta);
    }
}