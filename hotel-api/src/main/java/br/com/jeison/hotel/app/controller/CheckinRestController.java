package br.com.jeison.hotel.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jeison.hotel.app.model.CheckinDTO;
import br.com.jeison.hotel.app.service.CheckinService;

@CrossOrigin(origins = { "http://localhost:9000" })
@RestController
@RequestMapping(value = "/api/checkin")
public class CheckinRestController {
	
	private CheckinService checkinService;
	
	@Autowired
	public CheckinRestController(CheckinService checkinService) {
		this.checkinService = checkinService;
	}
	
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid CheckinDTO checkinDTO) {
    	
        checkinService.save(checkinDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid CheckinDTO checkinDTO) {
    	
        checkinService.update(checkinDTO);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{status}")
    public ResponseEntity<List<CheckinDTO>> findPersonInOutHotel(@PathVariable("status") String status) {
    	
        return ResponseEntity.ok().body(checkinService.findCheckinInOut(status));
    }

}
