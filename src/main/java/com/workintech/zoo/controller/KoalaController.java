package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getKoalasList(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id){
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        if(koalas.containsKey(id)){
            return koalas.get(id);
        }else{
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala){
        if(koala.getId()<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id , @RequestBody Koala koala){
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        if(koalas.containsKey(id)){
            koalas.put(id,koala);
        } else {
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        if(id<=0){
            throw new ZooException("Id cannot be negative or 0 ", HttpStatus.BAD_REQUEST);
        }
        Koala koala = koalas.get(id);
        if(koala!=null){
            koalas.remove(id);
        }else {
            throw new ZooException("Cannot find " + id + " kangaroo ", HttpStatus.NOT_FOUND);
        }

        return koala;
    }
}
