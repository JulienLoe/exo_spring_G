package com.example.exospringgg.controllers;

import com.example.exospringgg.models.PokemonDTO;
import com.example.exospringgg.services.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
@RequiredArgsConstructor
@Slf4j
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping // GET http://localhost:8080/home
    public String getHomePage(Model model) {
        List<String> pokemons = pokemonService.getPostsJsonNode();

        model.addAttribute("pokemons", pokemons);

        return "pokemons/pokemon";
    }

    @GetMapping("/{PokemonId}")
    public String getDetails (@PathVariable("PokemonId") String name, Model model){
        PokemonDTO pokemons = pokemonService.getPokemon(name);

        model.addAttribute("abilities", pokemons.getAbilities());
        model.addAttribute("name", pokemons.getSpecies());
        model.addAttribute("sprites", pokemons.getSprites());
        model.addAttribute("weight", pokemons.getWeight());
        model.addAttribute("height", pokemons.getHeight());
        model.addAttribute("types", pokemons.getTypes());

        System.out.println(pokemons.getAbilities());
        System.out.println(pokemons.getSpecies());
        return "pokemons/pokemon";

    }

    @PostMapping("/add")
    public String addDogHandler(@PathVariable(required = false) String name, String nameInput) {
        System.out.println(nameInput);

        return "redirect:/home/" + nameInput;
    }



//    @GetMapping("avec-param") // GET http://localhost:8080/home/avec-param OU GET http://localhost:8080/home/avec-param?paramA=sdqdqdqd
//    public String getHomePageWithParam(Model model, @RequestParam(value = "paramA", defaultValue = "") String qqch) {
//        model.addAttribute("testParam", "blabla");
//        model.addAttribute("testSwitch", "D");
//        model.addAttribute("testParams", List.of("John", "Maria", "Chloée"));
//        model.addAttribute("contact", ContactDTO.builder().firstname("Johnny").lastname("SMITH").build());
//
//        return "home-avec-param";
//    }
//
//    @GetMapping("/{nomQuonChoisi}")
//    public String getParamPage(@PathVariable("nomQuonChoisi") String autreNom){
//
//
//        return "redirect:/home/avec-param?paramA=" + autreNom;
//    }
//
//    @GetMapping("/add")
//    public String getForm(Model model) {
//        model.addAttribute("contact", ContactDTO.builder().build());
//
//        return "home-add";
//    }
//
//    @PostMapping("/add")
//    public String postHandler(@Valid ContactDTO contactDTO, BindingResult results) {
//        if (results.hasErrors()) {
//            results.getAllErrors().forEach(err -> log.error(err.toString()));
//
//            return "home-add";
//        }
//
//        // Ajoute ma personne
//
//        return "redirect:/home/avec-param";
//    }


}
