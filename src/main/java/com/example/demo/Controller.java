package com.example.demo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"*"})
public class Controller {

    @Autowired
    private final EtudiantRepository etudiantRepository;

    public Controller(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @RequestMapping("/")
    public String helloWorld(){
        System.out.println(">>> in controller : helloWorld");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "Hello World from Spring Boot ! Current datetime is "
                        + sdf.format(calendar.getTime()));
        return jsonObject.toString();
    }

    @RequestMapping(value="/listeEtudiants")
    List<Etudiant> all(){
        System.out.println(">>> in controller : all");
        return etudiantRepository.findAll();
    }

    @RequestMapping(value="/etudiant/{id}")
    Optional<Etudiant> some(@PathVariable Long id) {
        System.out.println(">>> in controller : /etudiant/{id}");
        return etudiantRepository.findById(id);
    }


}


