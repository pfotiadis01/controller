package com.mft.interhip8.service;

import com.mft.interhip8.dto.ClientDetailsDTO;
import com.mft.interhip8.entity.ClientDetails;
import com.mft.interhip8.repository.ClientDetailsRepository;
import com.mft.interhip8.controller.ClientDetailsMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import java.util.List;

@Service
public class ClientDetailsService {

    private final ClientDetailsRepository clientDetailsRepository;
    private final ClientDetailsMapper clientDetailsMapper;

    @Autowired
    public ClientDetailsService(ClientDetailsRepository clientDetailsRepository, ClientDetailsMapper clientDetailsMapper) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.clientDetailsMapper = clientDetailsMapper;
    }


    public List<ClientDetailsDTO> getAllClientDetails() {

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        clientDetailsRepository.findAll().forEach(clientDetailsList::add);
        return clientDetailsMapper.toDtoList(clientDetailsList);
    }

    public ClientDetailsDTO getClientDetailsById(Long id) {
        ClientDetails clientDetails = clientDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ClientDetails not found with id: " + id));
        return clientDetailsMapper.toDto(clientDetails);
    }

    public List<ClientDetailsDTO> getClientDetailsByClientUserName(String username) {
        List<ClientDetails> clientDetailsList = clientDetailsRepository.findByClientUserName(username);
        return clientDetailsMapper.toDtoList(clientDetailsList);
    }

    public ClientDetailsDTO createClientDetails(ClientDetailsDTO clientDetailsDTO) {
        ClientDetails clientDetails = clientDetailsMapper.toEntity(clientDetailsDTO);
        ClientDetails savedClientDetails = clientDetailsRepository.save(clientDetails);
        return clientDetailsMapper.toDto(savedClientDetails);
    }


    public ClientDetailsDTO updateClientDetails(Long id, ClientDetailsDTO clientDetailsDTO) {
        ClientDetails existingClientDetails = clientDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ClientDetails not found with id: " + id));

        ClientDetails updatedClientDetails = clientDetailsRepository.save(existingClientDetails);
        return clientDetailsMapper.toDto(updatedClientDetails);
    }

    public void deleteClientDetails(Long id) {
        clientDetailsRepository.deleteById(id);
    }
}
