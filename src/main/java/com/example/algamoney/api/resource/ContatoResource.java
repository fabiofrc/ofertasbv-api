package com.example.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Contato;
import com.example.algamoney.api.repository.ContatoRepository;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @CrossOrigin(maxAge = 10, allowCredentials = "false") //origins = "http://localhost:8080/categorias")
    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Contato> listar() {
        return contatoRepository.findAll();
    }

    @PostMapping("/anexo")
    public String uploadAnexo(@RequestParam MultipartFile anexo) throws FileNotFoundException, IOException {
        try (OutputStream out = new FileOutputStream("C:\\Users\\fabio\\Pictures\\upload\\" + anexo.getOriginalFilename())) {
            out.write(anexo.getBytes());
        }
        return "ok - " + anexo.getOriginalFilename();
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
        Contato contatoSalva = contatoRepository.save(contato);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, contatoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalva);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<Contato> buscarPeloCodigo(@PathVariable Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        return contato.isPresent() ? ResponseEntity.ok(contato.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato) {
        try {
            Contato contatoSalva = contatoRepository.getOne(id);
            if (contatoSalva == null) {
                throw new EmptyResultDataAccessException(1);
            }
            BeanUtils.copyProperties(contato, contatoSalva, "id");
            contatoRepository.save(contatoSalva);
            return ResponseEntity.ok(contatoSalva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
