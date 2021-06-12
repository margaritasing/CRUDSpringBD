package com.example.crudspringbd.Control;

import com.example.crudspringbd.Entidad.Animal;
import com.example.crudspringbd.Servicio.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("animales")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public String getAnimales(Model modelo){
        modelo.addAttribute("colores",animalService.getColores());
        modelo.addAttribute("animales", animalService.getAnimales());
        return "ver-animales";
    }
    @GetMapping("/buscar")
    public String buscar(@RequestParam Optional<String> nombreORid, @RequestParam Optional<String> color, @RequestParam Optional<Integer> edad){
        if(color.isPresent() && !color.get().equals("0"))
            return "redirect:/animales/color/"+color.get();
        if(edad.isPresent() && edad.get()>0)
            return "redirect:/animales/edad/0/"+edad.get();
        if(nombreORid.isPresent() && !nombreORid.get().isEmpty())
            try{
                Integer id = Integer.parseInt(nombreORid.get());
                return "redirect:/animales/id/"+id;
            }catch (NumberFormatException e){
                return "redirect:/animales/nombre/"+nombreORid.get();
            }
        return "redirect:/animales";
    }
    @GetMapping("/nombre/{nombre}")
    public String buscarPorNombre(Model modelo, @PathVariable String nombre){
        modelo.addAttribute("colores",animalService.getColores());
        modelo.addAttribute("animales",animalService.findByNombreContaining(nombre));
        return "ver-animales";
    }
    @GetMapping("/color/{color}")
    public String buscarPorColor(ModelMap modelo, @PathVariable String color){
        modelo.addAttribute("colores",animalService.getColores());
        modelo.addAttribute("animales",animalService.findByColor(color));
        return "ver-animales";
    }
    @GetMapping("/id/{idanimal}")
    public String buscarPorID(Model modelo, @PathVariable String idanimal){
        modelo.addAttribute("colores",animalService.getColores());
        modelo.addAttribute("animales", animalService.getAnimalByIdanimal(idanimal));
        return "ver-animales";
    }

    @GetMapping("/nuevo")
    public String newAnimal(Model modelo){
        modelo.addAttribute("animal",new Animal());
        return "guardar-animal";
    }
    @GetMapping("/editar/{idanimal}")
    public String editAnimal(Model modelo, @PathVariable String idanimal){
        List<Animal> animal = animalService.getAnimalByIdanimal(idanimal);
        modelo.addAttribute("animal", animal.get(0));
        return "guardar-animal";
    }
    @GetMapping("/edad/{edadDesde}/{edadHasta}")
    public String findAnimalesByEdadBetween(Model modelo, @PathVariable Integer edadDesde, @PathVariable Integer edadHasta){
        modelo.addAttribute("colores",animalService.getColores());
        modelo.addAttribute("animales", animalService.findAnimalesByEdadBetween(edadDesde, edadHasta));
        return "ver-animales";
    }

    @DeleteMapping("/borrar/{idanimal}")
    public String deleteAnimal(@PathVariable Integer idanimal){
        animalService.deleteById(idanimal);
        return "success";
    }

    @PostMapping("/guardar")
    public String saveAnimal(@ModelAttribute Animal animal){
        animalService.save(animal);
        return "redirect:/animales";
    }

}
