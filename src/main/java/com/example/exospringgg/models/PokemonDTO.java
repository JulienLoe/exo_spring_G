package com.example.exospringgg.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

@Builder
public class PokemonDTO {


    private List<String> abilities = new ArrayList<>();
    private List<String> species = new ArrayList<>();
    private List<String> sprites = new ArrayList<>();
    private List<String> weight = new ArrayList<>();
    private List<String> height = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    private List<Integer> id = new ArrayList<>();



}
