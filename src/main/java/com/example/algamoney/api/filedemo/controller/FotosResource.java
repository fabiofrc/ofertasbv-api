package com.example.algamoney.api.filedemo.controller;

import com.example.algamoney.api.filedemo.service.Disco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/fotos")
public class FotosResource {

    @Autowired
    private Disco disco;

    @PostMapping
    public void upload(@RequestParam MultipartFile foto) {
        disco.salvarFoto(foto);
    }

}
