package com.sarabarbara.adoption.services;

import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.models.Pet;
import com.sarabarbara.adoption.repositories.OwnerRepository;
import com.sarabarbara.adoption.repositories.PetRepository;
import com.sarabarbara.adoption.utils.exceptions.owner.OwnerNotFoundException;
import com.sarabarbara.adoption.utils.exceptions.pet.PetNotFoundException;
import com.sarabarbara.adoption.utils.exceptions.pet.PetValidateException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * PetService class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 08/04/2025
 */

@Service
@AllArgsConstructor
public class PetService {

    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    private PetRepository petRepository;
    private OwnerRepository ownerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Method to create a pet
     *
     * @param pet the pet's data
     *
     * @return the created pet
     *
     * @throws PetValidateException the {@link PetValidateException}
     */

    public Pet createPet(@NotNull Pet pet) throws PetValidateException {

        logger.info("Creating pet...");
        photoUrlValidator(pet.getPhotoUrl());

        logger.info("Pet created successfully");
        return petRepository.save(pet);
    }

    /**
     * Method to get the list of all pets
     *
     * @param page the page to search
     * @param size the size of the page
     *
     * @return the list of all pets
     */

    public List<Pet> petList(int page, int size) {

        logger.info("Pet list. Page {}, size {}", page, size);

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Pet> pageResult = petRepository.findAll(pageRequest);

        List<Pet> petList = pageResult.getContent();

        logger.info("Pet list: {}. ", petList);
        return petList;

    }


    /**
     * Method to search a pet
     *
     * @param name             the name of the pet
     * @param birthDate        the birthdate of the pet
     * @param breed            the breed of the pet
     * @param weight           the weight of the pet
     * @param chip             if the pet has chip
     * @param adopted          if the pet is adopted
     * @param phoneNumberOwner the phone number of the owner
     * @param photoUrl         the photo url of the pet
     * @param page             the page
     * @param size             the size of the page
     *
     * @return the pet searched
     */

    public List<Pet> searchPet(String name, LocalDate birthDate, String breed, Float weight, Boolean chip,
                               Boolean adopted, String phoneNumberOwner, String photoUrl, int page, int size) {

        logger.info("Searching pet...");

        if (page < 1) {
            throw new IllegalArgumentException("Page index must be at least 1");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<Pet> searchedPets = petRepository.searchPets(name, birthDate, breed, weight, chip, adopted,
                phoneNumberOwner, photoUrl, pageRequest);

        logger.info("Pet found: {}", searchedPets);
        return searchedPets.getContent();

    }

    /**
     * Method to see the sheet of the pet
     * In this method it searched the id of the pet to see their sheet
     *
     * @param idPet the id of the pet
     *
     * @return the id of the pet
     */

    public Pet petSheet(Long idPet) {

        logger.info("Pet sheet for id {}", idPet);

        Optional<Pet> pet = petRepository.findById(idPet);

        return pet.orElse(null);
    }

    /**
     * Method to update pet's data
     *
     * @param phoneNumber the phone number of the owner
     * @param petName     the name of the pet to be updated
     * @param newInfo     the new info to be updated
     *
     * @return the updated pet
     *
     * @throws OwnerNotFoundException the {@link OwnerNotFoundException}
     * @throws PetNotFoundException   the {@link PetNotFoundException}
     */

    public Pet updatePet(String phoneNumber, @NotNull String petName, @NotNull Pet newInfo) throws OwnerNotFoundException,
            PetNotFoundException {

        logger.info("Updating pet with telephone number of the owner {}", phoneNumber);

        Owner optionalOwner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new OwnerNotFoundException("Can't update pet: Owner not found"));

        Pet optionalPet = petRepository.findByNameAndOwner(petName, optionalOwner)
                .orElseThrow(() -> new PetNotFoundException("Can't update pet: Pet not found"));

        if (!optionalPet.getIsAdopted()) {
            throw new IllegalStateException("Can't update pet: Pet is not adopted yet.");
        }

        logger.info("New pet info: {}", newInfo);

        // ignores the id field
        modelMapper.typeMap(Pet.class, Pet.class)
                .addMappings(mapper -> mapper.skip(Pet::setIdPet));

        // ignores the null fields
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        /* copies the values of the Client object (newInfo, any non-null field)
        to the existingClient object. */

        modelMapper.map(newInfo, optionalPet);

        logger.info("Updating pet of the owner {}...", optionalOwner.getName());
        petRepository.save(optionalPet);


        logger.info("Pet {} updated successfully", petName);
        return optionalPet;
    }

    /**
     * Method to delete a pet
     *
     * @param phoneNumber the phone number of the owner
     * @param petName     the name of the pet
     *
     * @throws PetNotFoundException the {@link PetNotFoundException}
     */

    public void deletePet(String phoneNumber, String petName) throws PetNotFoundException {

        Owner optionalOwner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new OwnerNotFoundException("Can't update pet: Owner not found"));

        List<Pet> ownerPets = optionalOwner.getPets();

        Pet petToDelete = null;
        for (Pet pet : ownerPets) {

            if (pet.getName().equals(petName)) {

                petToDelete = pet;
                break;
            }
        }

        if (petToDelete == null) {
            throw new PetNotFoundException("Pet with name " + petName + " not found for owner with phone number " + phoneNumber);
        }

        logger.info("Deleting pet: {}", petToDelete.getName());

        petRepository.deleteById(petToDelete.getIdPet());

        logger.info("Pet with id {} (owner's phone number: {}) has been deleted successfully.",
                petToDelete.getIdPet(), phoneNumber);
    }

    // Complementary methods

    /**
     * Validates if the photoUrl is taken
     *
     * @param photoUrl the company
     */

    private void photoUrlValidator(String photoUrl) {

        Optional<Pet> optionalCompany = petRepository.findByPhotoUrl(photoUrl);

        if (optionalCompany.isPresent()) {

            logger.error("The photoUrl {} is already taken.", photoUrl);
            throw new PetValidateException("The photoUrl " + photoUrl + " is already taken.");
        }

        logger.info("The photoUrl {} is available", photoUrl);
    }

}
