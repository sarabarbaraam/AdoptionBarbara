package com.sarabarbara.adoption.controllers;

import com.sarabarbara.adoption.dto.pet.*;
import com.sarabarbara.adoption.models.Pet;
import com.sarabarbara.adoption.responses.SearchResponse;
import com.sarabarbara.adoption.responses.pet.CreatePetResponse;
import com.sarabarbara.adoption.responses.pet.PetSheetResponse;
import com.sarabarbara.adoption.responses.pet.UpdatePetResponse;
import com.sarabarbara.adoption.services.PetService;
import com.sarabarbara.adoption.sheets.PetSheet;
import com.sarabarbara.adoption.utils.exceptions.pet.PetValidateException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.sarabarbara.adoption.utils.mappers.PetMapper.*;

/**
 * PetController class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 08/04/2025
 */

@RestController
@AllArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;

    /**
     * The creation pet controller
     *
     * @param pet the pet to create
     *
     * @return the created pet
     */

    @PostMapping("/create")
    public ResponseEntity<CreatePetResponse> createPet(@NotNull @Validated @RequestBody Pet pet) {

        try {

            logger.info("Creating pet started");

            Pet createdpet = petService.createPet(pet);

            PetCreateDTO createPetDTO = toPetCreateDTOMapper(createdpet.getName(), createdpet.getBirthDate(),
                    createdpet.getBreed(), createdpet.getWeight(), createdpet.getHasChip(), createdpet.getIsAdopted(),
                    createdpet.getOwner(), createdpet.getPhotoUrl());

            logger.info("Creating pet finished");

            logger.info("Pet created successfully: {}", createPetDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreatePetResponse(true, createPetDTO,
                    "Pet created successfully"));
        } catch (PetValidateException cv) {

            logger.error("Can't create the pet: A conflict had occurred {}", cv.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CreatePetResponse(false, null,
                            cv.getMessage()));

        } catch (Exception e) {

            logger.error("Can't create the pet: Some internal error occurred {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreatePetResponse(false, null,
                            e.getMessage()));
        }

    }

    /**
     * The pets list controller
     *
     * @param page the page
     * @param size the size of the page
     *
     * @return the list of all pets
     */

    @GetMapping
    public ResponseEntity<SearchResponse<PetDTO>> petList(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "5") int size) {

        try {
            logger.info("List of pets started");

            List<Pet> petList = petService.petList(page - 1, size);
            int totalPages = (int) Math.ceil((double) petList.size() / size);

            if (petList.isEmpty()) {

                logger.info("Pet list finished without content");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            List<PetDTO> petDTO = toPetDTOListMapper(petList);

            logger.info("List of pets:");
            petDTO.forEach(pet -> logger.info("  - name: {}, breed: {}, isAdopted: {}",
                    pet.getName(), pet.getBreed(), pet.getIsAdopted()));

            return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse<>(
                    petDTO, petDTO.size(), page, totalPages, "Successful"));

        } catch (Exception e) {

            logger.error("Can't get the pet list. Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SearchResponse<>(null, 0, 0, 0,
                            e.getMessage()));
        }
    }

    /**
     * The search pet controller
     *
     * @param name             the name of the pets
     * @param birthDate        the birthdate of the pets
     * @param breed            the breed of the pets
     * @param weight           the weight of the pets
     * @param chip             if the pets has chip
     * @param adopted          if the pets is adopted
     * @param phoneNumberOwner the phone number of the owner
     * @param photoUrl         the url of the photo of the pets
     *
     * @return the searched pet
     */

    @GetMapping("/search")
    public ResponseEntity<SearchResponse<PetSearchDTO>> searchPet(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                                                                  @RequestParam(required = false) String breed,
                                                                  @RequestParam(required = false) Float weight,
                                                                  @RequestParam(required = false) Boolean chip,
                                                                  @RequestParam(required = false) Boolean adopted,
                                                                  @RequestParam(required = false) String phoneNumberOwner,
                                                                  @RequestParam(required = false) String photoUrl,
                                                                  @RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "5") int size) {


        try {

            logger.info("Searching pet started");

            List<Pet> searchedPet = petService.searchPet(name, birthDate, breed, weight, chip, adopted,
                    phoneNumberOwner, photoUrl, page, size);

            if (searchedPet.isEmpty()) {

                logger.info("No pets found");

                logger.info("Searching pet finished without content");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            List<PetSearchDTO> petSearchDTO = toPetSearchDTOMapper(searchedPet);

            int totalPage = (int) Math.ceil((double) searchedPet.size() / size);

            SearchResponse<PetSearchDTO> response = new SearchResponse<>(
                    petSearchDTO, searchedPet.size(), page, totalPage, "Successfully");

            logger.info("Pet found:");
            petSearchDTO.forEach(pet -> logger.info("  - name: {}, breed: {}, adopted: {}",
                    pet.getName(), pet.getBreed(), pet.getIsAdopted()));

            logger.info("Searching pet finished");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {

            logger.error("Can't search pet: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SearchResponse<>(null, 0, 0, 0,
                            e.getMessage()));
        }
    }

    /**
     * The pet sheet controller
     *
     * @param idPet the id of the pet
     *
     * @return the sheet of the pet
     */

    @GetMapping("/profile/{idPet}")
    public ResponseEntity<PetSheetResponse> petSheet(@PathVariable Long idPet) {

        try {

            logger.info("petSheet started");

            Pet pet = petService.petSheet(idPet);

            PetSheet petSheet = toPetSheetMapper(pet);

            logger.info("Pet sheet for id {}: {}", idPet, petSheet);
            return ResponseEntity.status(HttpStatus.OK).body(new PetSheetResponse(true, petSheet,
                    "Successfully"));

        } catch (Exception e) {

            logger.error("Can't load pet's sheet: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PetSheetResponse(false, null, e.getMessage()));

        }
    }

    /**
     * The update pet controller
     *
     * @param phoneNumber the phoneNumber of the owner to search for
     * @param pet         the pet's data
     *
     * @return the updated pet
     */

    @PatchMapping("/{phoneNumber}/update")
    public ResponseEntity<UpdatePetResponse> updatePet(@PathVariable String phoneNumber,
                                                       @RequestParam String petName,
                                                       @RequestBody Pet pet) {

        try {

            logger.info("Updating pet started");

            Pet updatedPet = petService.updatePet(phoneNumber, petName, pet);

            PetUpdateDTO petUpdateDTO = toPetUpdateDTOMapper(updatedPet);

            logger.info("Pet updated successfully: {}", petUpdateDTO);

            logger.info("Updating pet finished");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdatePetResponse(true, petUpdateDTO, "Pet updated successfully"));

        } catch (Exception e) {

            logger.error("Can't update pet: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdatePetResponse(false, null, e.getMessage()));
        }
    }

    /**
     * The Delete Controller
     *
     * @param phoneNumber the identifier
     * @param petName     the name of the pet
     *
     * @return a message
     */

    @DeleteMapping("/{phoneNumber}/delete")
    public ResponseEntity<String> deletePet(@PathVariable String phoneNumber,
                                            @RequestParam String petName) {

        try {

            logger.info("Deleting pet started");

            petService.deletePet(phoneNumber, petName);

            logger.info("Deleting pet finished");
            return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully");

        } catch (Exception e) {

            logger.error("Can't delete pet: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * The Youngest Pets Controller
     *
     * @param limit the number limit (the x youngest pets)
     *
     * @return the x youngest pets
     */

    @GetMapping("/pets/youngest")
    public ResponseEntity<List<YoungestPetDTO>> youngestPets(@RequestParam(defaultValue = "20") int limit) {

        try {

            logger.info("Searching the {} youngest pets", limit);

            List<Pet> youngestPets = petService.findYoungestPets(limit);
            List<YoungestPetDTO> youngestPetsDTOs = toYoungestPetDTOMapper(youngestPets);

            return ResponseEntity.status(HttpStatus.OK).body(youngestPetsDTOs);

        } catch (Exception e) {

            logger.error("Error searching youngest pets: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
