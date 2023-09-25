package com.example.exospringgg.controllers;


import com.example.exospringgg.models.PokemonDTO;
import com.example.exospringgg.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/v1/demo")
    public class PokemonRestController {
    private final PokemonService pokemonService;

    @GetMapping("in-string")
    public List<String> getPokemon() {
//        postService.getPostsString();
//        postService.getPostsMap();
//        postService.getPostsJsonNode();
//        pokemonService.getPosts();
        return pokemonService.getPostsJsonNode();
    }

//    @GetMapping("/{pokemonId}")
//    public PokemonDTO getDetails (@PathVariable("pokemonId") String name){
//        List<String> foundPokemon = pokemonService.getPokemon(name);
//
//
//            return (PokemonDTO) foundPokemon;
//
//
//    }
    @GetMapping("pokemon/{pokeName}")
    public PokemonDTO getPokemonByName(@PathVariable String pokeName) {
        return pokemonService.getPokemon(pokeName);
    }

//    @PostMapping("pokemonPost/{pokeName}")
//    public ResponseEntity<String> addPokemon(@RequestBody PokemonDTO newPokemon) {
//        pokemonService.addPokemon(newPokemon);
//
//        return new ResponseEntity<String>(  HttpStatus.CREATED);
//    }

}

