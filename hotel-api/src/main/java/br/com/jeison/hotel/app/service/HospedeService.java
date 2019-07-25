package br.com.jeison.hotel.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jeison.hotel.app.entity.Hospede;
import br.com.jeison.hotel.app.model.HospedeDTO;
import br.com.jeison.hotel.app.repository.HospedeRepository;

@Service
public class HospedeService {

    private HospedeRepository hospedeRepository;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public Page<HospedeDTO> getAll(Pageable paginacao) {
    	
        return HospedeDTO.of(this.hospedeRepository.findAll(paginacao), paginacao);
    }
    
    public HospedeDTO findById(long id) {
    	
    	Hospede hospede = hospedeRepository.findById(id).orElse(null);
    	return (hospede != null) ? HospedeDTO.of(hospede) : null;
    }

    public void save(HospedeDTO hospedeDto) {
    	
        this.hospedeRepository.save(hospedeDto.toObject());
    }

    public void delete(Long id) {
    	
        this.hospedeRepository.deleteById(id);
    }

    public void update(HospedeDTO hospedeDto) {
    	
        this.hospedeRepository.save(hospedeDto.toObject());
    }

	public List<HospedeDTO> findByNomeOrDocumentoOrTelefone(String filtro) {

		return HospedeDTO.of(this.hospedeRepository.findByNomeOrDocumentoOrTelefone(filtro));
	}

}

