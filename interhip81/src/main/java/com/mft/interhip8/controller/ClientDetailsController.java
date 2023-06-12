package com.mft.interhip8.controller;

import com.mft.interhip8.dto.ClientDetailsDTO;
import com.mft.interhip8.entity.ClientDetails;
import com.mft.interhip8.service.ClientDetailsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDetailsController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping("")
    public List<ClientDetailsDTO> getAllClientDetails() {
        return clientDetailsService.getAllClientDetails();
    }

    @GetMapping("/{username}")
    public List<ClientDetailsDTO> getClientDetailsByClientUserName(@PathVariable String username) {
        return clientDetailsService.getClientDetailsByClientUserName(username);
    }

    @GetMapping("/{id}")
    public ClientDetailsDTO getClientDetailsById(@PathVariable Long id) {
        return clientDetailsService.getClientDetailsById(id);
    }

    @PostMapping
    public ClientDetailsDTO createClientDetails(@RequestBody ClientDetailsDTO clientDetailsDTO) {
        return clientDetailsService.createClientDetails(clientDetailsDTO);
    }

    @PutMapping("/{id}")
    public ClientDetailsDTO updateClientDetails(@PathVariable Long id, @RequestBody ClientDetailsDTO clientDetailsDTO) {
        return clientDetailsService.updateClientDetails(id, clientDetailsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClientDetails(@PathVariable Long id) {
        clientDetailsService.deleteClientDetails(id);
    }
}

