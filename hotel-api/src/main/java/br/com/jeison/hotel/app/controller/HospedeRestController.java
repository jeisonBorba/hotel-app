package br.com.jeison.hotel.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jeison.hotel.app.model.HospedeDTO;
import br.com.jeison.hotel.app.service.HospedeService;

@CrossOrigin(origins = { "http://localhost:9000" })
@RestController
@RequestMapping(value = "/api/hospede")
public class HospedeRestController {

    private HospedeService hospedeService;

    @Autowired
    public HospedeRestController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping
    public ResponseEntity<Page<HospedeDTO>> findAll(@RequestParam(value = "pagina", required = false, defaultValue = "1") int pagina,
                                                    @RequestParam(value = "total", required = false, defaultValue = "10") int total,
                                                    @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
                                                    @RequestParam(value = "campo", required = false, defaultValue = "id") String campo) {
    	
        Pageable p = PageRequest.of(pagina - 1, total, Sort.Direction.fromString(order), campo);
        return ResponseEntity.ok(hospedeService.getAll(p));
    }
    
    @GetMapping(value = "/byFiltro/{filtro}")
    public ResponseEntity<List<HospedeDTO>> findByFiltro(@PathVariable("filtro") final String filtro) {
    	
    	return ResponseEntity.ok().body(hospedeService.findByNomeOrDocumentoOrTelefone(filtro));
    }
    
    @GetMapping(value = "/{id}")
    
    public ResponseEntity<HospedeDTO> getById(@PathVariable("id") Long id) {
    	return ResponseEntity.ok().body(hospedeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody HospedeDTO hospedeDto) {
    	
        hospedeService.save(hospedeDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
    	
        hospedeService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody HospedeDTO hospedeDto) {
    	
        hospedeService.update(hospedeDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}

