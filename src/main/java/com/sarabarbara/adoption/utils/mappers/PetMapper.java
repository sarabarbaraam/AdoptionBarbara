package com.sarabarbara.adoption.utils.mappers;

import com.sarabarbara.adoption.dto.pet.*;
import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.models.Pet;
import com.sarabarbara.adoption.sheets.PetSheet;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * PetMapper class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 08/04/2025
 */

@Builder
public class PetMapper {

    /**
     * The private constructor
     */

    private PetMapper() {

    }

    /**
     * The pets to PetCreateDTO Mapper
     *
     * @param name      the name of the pets
     * @param birthDate the birthdate of the pets
     * @param breed     the breed of the pets
     * @param weight    the weight of the pets
     * @param chip      if the pets has chip
     * @param adopted   if the pets is adopted
     * @param owner     the owner of the pets
     * @param photoUrl  the url of the photo of the pets
     *
     * @return the created pets
     */

    public static PetCreateDTO toPetCreateDTOMapper(String name, LocalDate birthDate, String breed, float weight,
                                                    boolean chip, boolean adopted, Owner owner, String photoUrl) {

        return PetCreateDTO.builder()
                .name(name)
                .birthDate(birthDate)
                .breed(breed)
                .weight(weight)
                .hasChip(chip)
                .isAdopted(adopted)
                .owner(owner)
                .photoUrl(photoUrl)
                .build();
    }

    /**
     * The Pet to PetDTO Mapper
     *
     * @param petList the pet list
     *
     * @return the PetDTO
     */

    public static List<PetDTO> toPetDTOListMapper(@NotNull List<Pet> petList) {

        return petList.stream()
                .map(pet -> PetDTO.builder()
                        .idPet(pet.getIdPet())
                        .name(pet.getName())
                        .birthDate(pet.getBirthDate())
                        .breed(pet.getBreed())
                        .weight(pet.getWeight())
                        .hasChip(pet.getHasChip())
                        .isAdopted(pet.getIsAdopted())
                        .owner(pet.getOwner())
                        .photoUrl(pet.getPhotoUrl())
                        .build())
                .toList();
    }

    /**
     * The Pet to PetSearchDTO mapper
     *
     * @param searchedPet the searched pet
     *
     * @return the PetSearchDTO
     */

    public static List<PetSearchDTO> toPetSearchDTOMapper(@NotNull List<Pet> searchedPet) {

        return searchedPet.stream()
                .map(pet -> PetSearchDTO.builder()
                        .name(pet.getName())
                        .breed(pet.getBreed())
                        .isAdopted(pet.getIsAdopted())
                        .build())
                .toList();
    }

    /**
     * The Pet to PetSheet Mapper
     *
     * @param pet the pet
     *
     * @return the sheet of the pet
     */

    public static PetSheet toPetSheetMapper(@NotNull Pet pet) {

        return PetSheet.builder()
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .breed(pet.getBreed())
                .weight(pet.getWeight())
                .hasChip(pet.getHasChip())
                .isAdopted(pet.getIsAdopted())
                .owner(pet.getOwner())
                .photoUrl(pet.getPhotoUrl())
                .build();
    }

    /**
     * The Pet to PetUpdateDTO mapper
     *
     * @param updatePet the pet to be updated
     *
     * @return the PetUpdateDTO
     */

    public static PetUpdateDTO toPetUpdateDTOMapper(@NotNull Pet updatePet) {

        return PetUpdateDTO.builder()
                .name(updatePet.getName())
                .birthDate(updatePet.getBirthDate())
                .breed(updatePet.getBreed())
                .weight(updatePet.getWeight())
                .hasChip(updatePet.getHasChip())
                .isAdopted(updatePet.getIsAdopted())
                .owner(updatePet.getOwner())
                .photoUrl(updatePet.getPhotoUrl())
                .build();
    }

    public static List<YoungestPetDTO> toYoungestPetDTOMapper(@NotNull List<Pet> pets) {

        return pets.stream()
                .map(youngestPets -> YoungestPetDTO.builder()
                        .name(youngestPets.getName())
                        .birthDate(youngestPets.getBirthDate())
                        .build())
                .toList();
    }

}
