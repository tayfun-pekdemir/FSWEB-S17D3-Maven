package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getKangarooList(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id){
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        if(kangaroos.containsKey(id)){
            return kangaroos.get(id);
        }else{
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){
        if(kangaroo.getId()<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id,@RequestBody Kangaroo kangaroo){
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }

        if(kangaroos.containsKey(id)){
            kangaroos.put(id,kangaroo);
        } else {
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }

        if (kangaroo != null) {
            kangaroos.remove(id);
        } else {
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }
        return kangaroo;
    }
}
