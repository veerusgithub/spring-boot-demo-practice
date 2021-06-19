package com.veeru.conferenceapp.controllers;

import com.veeru.conferenceapp.models.Session;
import com.veeru.conferenceapp.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public Session getSessionByID(@PathVariable Long id){
        return sessionRepository.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Session createSession(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(name ="{id}" , method = RequestMethod.PUT)
    public Session updateSession(@PathVariable Long id,@RequestBody Session session){
        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return  sessionRepository.saveAndFlush(existingSession);
    }

    @RequestMapping(name = "{id}", method = RequestMethod.DELETE)
    public  void deleteSession(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

}
