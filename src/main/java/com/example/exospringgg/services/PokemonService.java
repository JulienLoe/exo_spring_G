package com.example.exospringgg.services;

import com.example.exospringgg.models.PokemonDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class PokemonService {
    private final RestTemplateBuilder builder;


    public ResponseEntity<String> getPostsString() {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<String> entityString = restTemplate
                .getForEntity("https://jsonplaceholder.typicode.com/posts", String.class);

        return entityString;
    }

    public ResponseEntity<Map> getPostsMap() {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<Map> entityString = restTemplate
                .getForEntity("/posts/1", Map.class);

        Integer userId = (Integer) entityString.getBody().get("userId");

        return entityString;
    }

//    public List<String> getNameJsonNode(String name) {
//        RestTemplate restTemplate = builder.build();
//
////        ResponseEntity<JsonNode> entityString = restTemplate
////                .getForEntity("/ditto", JsonNode.class);
//
////        List<String> titles = new ArrayList<>();
////
////        entityString.getBody().findPath("abilities").elements().forEachRemaining(e -> {
////            String title = e.findPath("title").asText();
////            titles.add(title);
////        });
//        String url = name;
//        ResponseEntity<JsonNode> entityString = restTemplate
//               .getForEntity( name, JsonNode.class);
//        List<String> titles = new ArrayList<>();
//
//        entityString.getBody().findPath("abilities").elements().forEachRemaining(e -> {
//            String title = e.findPath("name").asText();
//            titles.add(title);
//            System.out.println(titles);
//        });
//
//        return titles;
//    }

    public PokemonDTO getPokemon(String name) {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<JsonNode> responseJson = restTemplate
                .getForEntity("pokemon/" + name, JsonNode.class);

        List<String> abilityNames = new ArrayList<>();
        List<String> namePokemon = new ArrayList<>();
        List<String> sprites = new ArrayList<>();
        List<String> weights = new ArrayList<>();
        List<String> heights = new ArrayList<>();
        List<String> types = new ArrayList<>();

        String nameFound = responseJson.getBody().get("name").asText();

        responseJson.getBody().findPath("abilities").elements().forEachRemaining(a -> {
            abilityNames.add(a.findPath("ability").get("name").asText());
//                    namePokemon.add(a.findPath("species").get("name").asText());
        });

        String nameP = responseJson.getBody().findPath("species").findPath("name").asText();
            namePokemon.add(nameP);
//                    namePokemon.add(a.findPath("species").get("name").asText());
        ;

        String sprite = responseJson.getBody().findPath("sprites").findPath("back_shiny").asText();
        sprites.add(sprite);

        String weight = responseJson.getBody().findPath("weight").asText();
        weights.add(weight);

        String height = responseJson.getBody().findPath("height").asText();
        heights.add(height);

        responseJson.getBody().findPath("types").elements().forEachRemaining(a -> {
            types.add(a.findPath("type").get("name").asText());
//                    namePokemon.add(a.findPath("species").get("name").asText());
        });

        return PokemonDTO
                .builder()
                .abilities(abilityNames)
                .species(namePokemon)
                .sprites(sprites)
                .types(types)
                .weight(weights)
                .height(heights)
                .build();
    }

    public List<String> getPostsJsonNode() {
        RestTemplate restTemplate = builder.build();

//        ResponseEntity<JsonNode> entityString = restTemplate
//                .getForEntity("/ditto", JsonNode.class);

//        List<String> titles = new ArrayList<>();
//
//        entityString.getBody().findPath("abilities").elements().forEachRemaining(e -> {
//            String title = e.findPath("title").asText();
//            titles.add(title);
//        });

        ResponseEntity<JsonNode> entityString = restTemplate
                .getForEntity( "/ditto", JsonNode.class);
        List<String> titles = new ArrayList<>();

        entityString.getBody().findPath("abilities").elements().forEachRemaining(e -> {
            String title = e.findPath("name").asText();
            titles.add(title);
            System.out.println(titles);
        });

        return titles;
    }

    public ResponseEntity<PokemonDTO[]> getPosts() {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<PokemonDTO[]> entityString = restTemplate
                .getForEntity("/ditto", PokemonDTO[].class);

        return entityString;
    }

    public List<String> getPostsTitles() {
        RestTemplate restTemplate = builder.build();

        ResponseEntity<PokemonDTO[]> entityString = restTemplate
                .getForEntity("/ditto", PokemonDTO[].class);

        List<String> titles = Arrays.stream(entityString.getBody())
                .map(p -> p.getClass().getName())
                .collect(Collectors.toList());

        return titles;
    }

//    public PokemonDTO addPokemon(PokemonDTO pokemonData) {
//
//
//        PokemonDTO pokemon = pokemonData;
//
//        return pokemon;
//    }
}
