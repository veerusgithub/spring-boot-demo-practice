package com.veeru.conferenceapp.controllers;

import com.veeru.conferenceapp.models.Session;
import com.veeru.conferenceapp.models.Speaker;
import com.veeru.conferenceapp.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> getAllSpeakers(){
        return  speakerRepository.findAll();
    }
    @GetMapping("{id}")
    public Speaker getSpeaker(@PathVariable Long id){
        return speakerRepository.getById(id);
    }

    @PostMapping
    public Speaker createSpeaker(@RequestBody final Speaker speaker){
        return speakerRepository.save(speaker);
    }

    @RequestMapping(name ="{id}" , method = RequestMethod.PUT)
    public Speaker updateSession(@PathVariable Long id, @RequestBody Speaker  speaker){
        Speaker existingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return  speakerRepository.saveAndFlush(existingSpeaker);
    }

    @RequestMapping(name = "{id}", method = RequestMethod.DELETE)
    public  void deleteSpeaker(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

}
