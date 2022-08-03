package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;


    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilterByYear(
            @RequestParam(name="year", required= false) Integer year){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable long id){
        return new ResponseEntity(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value="/region")
    public ResponseEntity getWhiskyByRegion(@RequestParam(name="region", required = true) String region){
        return new ResponseEntity(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
    }

    @GetMapping(value="/age/distillery")
    public ResponseEntity getWhiskyByAgeAndDistillery(@RequestParam(name="age", required=true) String age, @RequestParam(name="distillery", required=true) String distillery){
        return new ResponseEntity(whiskyRepository.findByAgeAndDistilleryName(Integer.parseInt(age), distillery), HttpStatus.OK);
    }

}
