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
import com.example.algamoney.api.filedemo.payload.UploadFileResponse;
import com.example.algamoney.api.filedemo.service.FileStorageService;
import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.ProdutoRepository;
import com.example.algamoney.api.repository.filter.ProdutoFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    private final Logger logger = LoggerFactory.getLogger(ProdutoResource.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @CrossOrigin(maxAge = 10, allowCredentials = "false") //origins = "http://localhost:8080/categorias")
    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Produto> listar() {
        return produtoRepository.filtrarProdutos();
    }

    @GetMapping("/pesquisar")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public List<Produto> pesquisar(ProdutoFilter produtoFilter) {
        return produtoRepository.filtrar(produtoFilter);
    }

    @GetMapping("/subcategoria/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Produto> buscarCategoriaByCodigo(@PathVariable Long id) {
        List<Produto> produto = produtoRepository.filtrarProdutoBySubCategoriaById(id);
        return produto;
    }

    @GetMapping("/promocao/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Produto> buscarPromocaoByCodigo(@PathVariable Long id) {
        List<Produto> produto = produtoRepository.filtrarProdutoByPromocaoById(id);
        return produto;
    }

    @GetMapping("/pessoa/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Produto> buscarPessoaByCodigo(@PathVariable Long id) {
        List<Produto> produto = produtoRepository.filtrarProdutoByPessoaById(id);
        return produto;
    }

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        System.out.println("FileName: " + fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/produtos/download/").path(fileName).toUriString();

        System.out.println("Caminho upload: " + fileDownloadUri);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream().map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
        Produto produtoSalva = produtoRepository.save(produto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalva);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<Produto> buscarPeloCodigo(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.isPresent() ? ResponseEntity.ok(produto.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/codigobarra/{codigobarra}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public Produto buscarPessoaByCodBarra(@PathVariable String codigobarra) {
        Produto produto = produtoRepository.filtrarProdutoByCodBarra(codigobarra);
        return produto;
    }

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        try {
            Produto produtoSalva = produtoRepository.getOne(id);
            if (produtoSalva == null) {
                throw new EmptyResultDataAccessException(1);
            }
            BeanUtils.copyProperties(produto, produtoSalva, "id");
            produtoRepository.save(produtoSalva);
            return ResponseEntity.ok(produtoSalva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
