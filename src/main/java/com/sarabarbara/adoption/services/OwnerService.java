package com.sarabarbara.adoption.services;

import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.repositories.OwnerRepository;
import com.sarabarbara.adoption.utils.exceptions.owner.OwnerNotFoundException;
import com.sarabarbara.adoption.utils.exceptions.pet.PetValidateException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * OwnerService class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Service
@AllArgsConstructor
public class OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerService.class);

    private OwnerRepository ownerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Method to create an owner
     *
     * @param owner the owner's data
     *
     * @return the created owner
     *
     * @throws PetValidateException the {@link PetValidateException}
     */

    public Owner createOwner(@NotNull Owner owner) throws PetValidateException {

        logger.info("Creating owner...");
        phoneNumberValidator(owner.getPhoneNumber());

        logger.info("Owner created successfully");
        return ownerRepository.save(owner);
    }

    /**
     * Method to get the list of all owners
     *
     * @param page the page to search
     * @param size the size of the page
     *
     * @return the list of all owners
     */

    public List<Owner> ownerList(int page, int size) {

        logger.info("Owner list. Page {}, size {}", page, size);

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Owner> pageResult = ownerRepository.findAll(pageRequest);

        List<Owner> ownerList = pageResult.getContent();

        logger.info("Owner list: {}. ", ownerList);
        return ownerList;
    }

    /**
     * Method to search an owner
     *
     * @param name        the name of the owner
     * @param address     the address of the owner
     * @param phoneNumber the phone number of the owner
     * @param page        the page
     * @param size        the size of the page
     *
     * @return the searched owner
     */

    public List<Owner> searchOwner(String name, String address, String phoneNumber, int page, int size) {

        logger.info("Searching owner...");

        if (page < 1) {
            throw new IllegalArgumentException("Page index must be at least 1");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<Owner> searchedOwner = ownerRepository.searchOwners(name, address, phoneNumber, pageRequest);

        return searchedOwner.getContent();

    }

    /**
     * Method to see the sheet of the owner
     * In this method it searched the id of the owner to see their sheet
     *
     * @param idOwner the id of the owner
     *
     * @return the id of the owner
     */

    public Owner ownerSheet(Long idOwner) {

        logger.info("Owner sheet for id {}", idOwner);

        Optional<Owner> owner = ownerRepository.findById(idOwner);

        return owner.orElse(null);

    }

    /**
     * Method to update owner's data
     *
     * @param phoneNumber the phone number of the owner
     * @param newInfo     the new info to be updated
     *
     * @return the updated owner
     *
     * @throws OwnerNotFoundException the {@link OwnerNotFoundException}
     */

    public Owner updateOwner(String phoneNumber, Owner newInfo) throws OwnerNotFoundException {

        logger.info("Updating owner with telephone number {}", phoneNumber);
        Owner optionalOwner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new OwnerNotFoundException("Can't update owner: Owner not found"));

        logger.info("New owner info: {}", newInfo);

        // ignores the id field
        modelMapper.typeMap(Owner.class, Owner.class)
                .addMappings(mapper -> mapper.skip(Owner::setIdOwner));

        // ignores the null fields
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        /* copies the values of the Owner object (newInfo, any non-null field)
        to the existingOwner object. */

        modelMapper.map(newInfo, optionalOwner);

        logger.info("Updating owner {}...", optionalOwner.getName());
        ownerRepository.save(optionalOwner);


        logger.info("Owner {} updated successfully", optionalOwner);
        return optionalOwner;
    }

    /**
     * Method to delete an owner
     *
     * @param phoneNumber the phone number of the owner
     *
     * @throws OwnerNotFoundException the {@link OwnerNotFoundException}
     */

    public void deleteOwner(String phoneNumber) throws OwnerNotFoundException {

        Owner optionalOwner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new OwnerNotFoundException("Can't update owner: Owner not found"));

        Long id = optionalOwner.getIdOwner();

        logger.info("Deleting owner: {}", optionalOwner);
        ownerRepository.deleteById(id);

        logger.info("Owner with id {} (phone number: {}) has been deleted successfully.", id, phoneNumber);
    }

    // Complementary methods

    /**
     * Validates if the phone number is taken
     *
     * @param phoneNumber the phone number
     */

    private void phoneNumberValidator(String phoneNumber) {

        Optional<Owner> optionalPhoneNumber = ownerRepository.findByPhoneNumber(phoneNumber);

        if (optionalPhoneNumber.isPresent()) {

            logger.error("The phone number {} is already taken.", phoneNumber);
            throw new PetValidateException("The phone number " + phoneNumber + " is already taken.");
        }

        logger.info("The phone number {} is available", phoneNumber);
    }

}
