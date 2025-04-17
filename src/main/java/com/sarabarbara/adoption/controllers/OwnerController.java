package com.sarabarbara.adoption.controllers;

import com.sarabarbara.adoption.dto.owner.OwnerCreateDTO;
import com.sarabarbara.adoption.dto.owner.OwnerDTO;
import com.sarabarbara.adoption.dto.owner.OwnerSearchDTO;
import com.sarabarbara.adoption.dto.owner.OwnerUpdateDTO;
import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.responses.SearchResponse;
import com.sarabarbara.adoption.responses.owner.CreateOwnerResponse;
import com.sarabarbara.adoption.responses.owner.OwnerSheetResponse;
import com.sarabarbara.adoption.responses.owner.UpdateOwnerResponse;
import com.sarabarbara.adoption.services.OwnerService;
import com.sarabarbara.adoption.sheets.OwnerSheet;
import com.sarabarbara.adoption.utils.exceptions.owner.OwnerValidateException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sarabarbara.adoption.utils.mappers.OwnerMapper.*;

/**
 * OwnerController class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@RestController
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);

    private final OwnerService ownerService;

    /**
     * The creation owner controller
     *
     * @param owner the owner to create
     *
     * @return the created owner
     */

    @PostMapping("/create")
    public ResponseEntity<CreateOwnerResponse> createOwner(@NotNull @Validated @RequestBody Owner owner) {

        try {

            logger.info("Creating owner started");

            Owner createdOwner = ownerService.createOwner(owner);

            OwnerCreateDTO createOwnerDTO = toOwnerCreateDTOMapper(createdOwner.getName(), createdOwner.getAddress(),
                    createdOwner.getPhoneNumber(), createdOwner.getPets());

            logger.info("Creating owner finished");

            logger.info("Owner created successfully: {}", createOwnerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateOwnerResponse(true, createOwnerDTO,
                    "Owner created successfully"));

        } catch (OwnerValidateException ow) {

            logger.error("Can't create the owner: A conflict had occurred {}", ow.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CreateOwnerResponse(false, null,
                            ow.getMessage()));

        } catch (Exception e) {

            logger.error("Can't create the owner: Some internal error occurred {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateOwnerResponse(false, null,
                            e.getMessage()));
        }

    }

    /**
     * The owner list controller
     *
     * @param page the page
     * @param size the size of the page
     *
     * @return the list of all owners
     */

    @GetMapping
    public ResponseEntity<SearchResponse<OwnerDTO>> ownerList(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "10") int size) {

        try {
            logger.info("List of owner started");

            List<Owner> ownerList = ownerService.ownerList(page - 1, size);
            int totalPages = (int) Math.ceil((double) ownerList.size() / size);

            if (ownerList.isEmpty()) {

                logger.info("Owner list finished without content");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            List<OwnerDTO> ownerDTO = toOwnerDTOListMapper(ownerList);

            logger.info("List of owner:");
            ownerDTO.forEach(owner -> logger.info("  - name: {}, address: {}, phoneNumber: {}",
                    owner.getName(), owner.getAddress(), owner.getPhoneNumber()));

            return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse<>(
                    ownerDTO, ownerDTO.size(), page, totalPages, "Successful"));

        } catch (Exception e) {

            logger.error("Can't get the owner list. Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SearchResponse<>(null, 0, 0, 0,
                            e.getMessage()));
        }
    }

    /**
     * The search owner controller
     *
     * @param name        the name of the owner
     * @param address     the address of the owner
     * @param phoneNumber the phone number of the owner
     * @param page        the page
     * @param size        the size of the page
     *
     * @return the searched owner
     */

    @GetMapping("/search")
    public ResponseEntity<SearchResponse<OwnerSearchDTO>> searchOwner(@RequestParam(required = false) String name,
                                                                      @RequestParam(required = false) String address,
                                                                      @RequestParam(required = false) String phoneNumber,
                                                                      @RequestParam(defaultValue = "1") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {

        try {

            logger.info("Searching owner started");

            List<Owner> searchedOwner = ownerService.searchOwner(name, address, phoneNumber, page, size);

            if (searchedOwner.isEmpty()) {

                logger.info("No owners found");

                logger.info("Searching owner finished without content");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            List<OwnerSearchDTO> ownerSearchDTO = toOwnerSearchDTOMapper(searchedOwner);

            int totalPage = (int) Math.ceil((double) searchedOwner.size() / size);

            SearchResponse<OwnerSearchDTO> response = new SearchResponse<>(
                    ownerSearchDTO, searchedOwner.size(), page, totalPage, "Successfully");

            logger.info("Owners found:");
            ownerSearchDTO.forEach(owner -> logger.info("  - name: {}, phoneNumber: {}",
                    owner.getName(), owner.getPhoneNumber()));

            logger.info("Searching owner finished");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {

            logger.error("Can't search owner: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SearchResponse<>(null, 0, 0, 0,
                            e.getMessage()));
        }
    }

    /**
     * The  owner sheet controller
     *
     * @param idOwner the id of the owner
     *
     * @return the sheet of the owner
     */

    @GetMapping("/profile/{idOwner}")
    public ResponseEntity<OwnerSheetResponse> ownerSheet(@PathVariable Long idOwner) {

        try {

            logger.info("OwnerSheet started");

            Owner owner = ownerService.ownerSheet(idOwner);

            OwnerSheet ownerSheet = toOwnerSheetMapper(owner);

            logger.info("Owner sheet for id {}: {}", idOwner, ownerSheet);
            return ResponseEntity.status(HttpStatus.OK).body(new OwnerSheetResponse(true, ownerSheet,
                    "Successfully"));

        } catch (Exception e) {

            logger.error("Can't load owner's sheet: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OwnerSheetResponse(false, null, e.getMessage()));

        }
    }

    /**
     * The update owner controller
     *
     * @param phoneNumber the phoneNumber of the owner to search for
     * @param owner       the owner's data
     *
     * @return the updated owner
     */

    @PatchMapping("/{phoneNumber}/update")
    public ResponseEntity<UpdateOwnerResponse> updateOwner(@PathVariable String phoneNumber,
                                                            @RequestBody Owner owner) {

        try {

            logger.info("Updating owner started");

            Owner updatedOwner = ownerService.updateOwner(phoneNumber, owner);

            OwnerUpdateDTO ownerUpdateDTO = toOwnerUpdateDTOMapper(updatedOwner);

            logger.info("Owner updated successfully: {}", ownerUpdateDTO);

            logger.info("Updating owner finished");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateOwnerResponse(true, ownerUpdateDTO, "Owner updated successfully"));

        } catch (Exception e) {

            logger.error("Can't update owner: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateOwnerResponse(false, null, e.getMessage()));
        }
    }

    /**
     * The Delete Controller
     *
     * @param phoneNumber the identifier
     *
     * @return a message
     */

    @DeleteMapping("/{phoneNumber}/delete")
    public ResponseEntity<String> deleteOwner(@PathVariable String phoneNumber) {

        try {

            logger.info("Deleting owner started");

            ownerService.deleteOwner(phoneNumber);

            logger.info("Deleting owner finished");
            return ResponseEntity.status(HttpStatus.OK).body("Owner deleted successfully");

        } catch (Exception e) {

            logger.error("Can't delete owner: Some internal error occurred. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
