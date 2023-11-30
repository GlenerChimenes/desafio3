package com.devsuperior.desafio3.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.entities.Client;
import com.devsuperior.desafio3.repositories.DesafioRepositpty;
import com.devsuperior.desafio3.servicos.exceptions.DatabaseException;
import com.devsuperior.desafio3.servicos.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DesafioServico {

	@Autowired
	private DesafioRepositpty repository;
	
	public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado"));
        return new ClientDTO(client);
    }
	
	@Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

	@Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
	        entity.setName(dto.getName());
	        entity.setCpf(dto.getCpf());
	        entity.setIncome(dto.getIncome());
	        entity.setBirthDate(dto.getBirthDate());
	        entity.setChildren(dto.getChildren());
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		  try {
	            Client entity = repository.getReferenceById(id);
	            copyDtoToEntity(dto, entity);
	            entity = repository.save(entity);
	            return new ClientDTO(entity);
	        }
	        catch (EntityNotFoundException e) {
	            throw new ResourceNotFoundException("Id não encontrado");
	        }

	}

	@Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Cliente não encontrado");
    	}
    	try {
            repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
	
}
