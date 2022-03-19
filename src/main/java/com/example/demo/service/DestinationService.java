package com.example.demo.service;

import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dto.DestinationDTO;
import com.example.demo.repository.DestinationRepository;

import java.util.List;
import java.util.Optional;

public class DestinationService {

    private static final DestinationRepository _destinationRepository = new DestinationRepository();

    public DestinationDAO createDestination(DestinationDTO destinationDTO) throws Exception {
        Optional<DestinationDAO> newDestination =_destinationRepository.createDestination(destinationDTO);
        if (newDestination.isPresent()) {
            return newDestination.get();
        } else {
            throw new Exception("new destination could not be added to db");
        }
    }

    public DestinationDAO deleteDestination(Long id) throws Exception {
        Optional<DestinationDAO> newDestination =_destinationRepository.deleteDestination(id);
        if (newDestination.isPresent()) {
            return newDestination.get();
        } else {
            throw new Exception("destination could not be deleted");
        }
    }

    public List<DestinationDAO> getAllDestinations() throws Exception {
        Optional<List<DestinationDAO>> newDestination =_destinationRepository.getAllDestinations();
        if (newDestination.isPresent()) {
            return newDestination.get();
        } else {
            throw new Exception("destination could not be deleted");
        }
    }
}
