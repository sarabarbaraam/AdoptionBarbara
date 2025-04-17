package com.sarabarbara.adoption.utils.mappers;

import com.sarabarbara.adoption.dto.owner.OwnerCreateDTO;
import com.sarabarbara.adoption.dto.owner.OwnerDTO;
import com.sarabarbara.adoption.dto.owner.OwnerSearchDTO;
import com.sarabarbara.adoption.dto.owner.OwnerUpdateDTO;
import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.models.Pet;
import com.sarabarbara.adoption.sheets.OwnerSheet;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * OwnerMapper class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Builder
public class OwnerMapper {

    /**
     * The private constructor
     */

    private OwnerMapper() {

    }

    public static OwnerCreateDTO toOwnerCreateDTOMapper(String name, String address, String phoneNumber,
                                                        List<Pet> pets) {

        return OwnerCreateDTO.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .pets(pets)
                .build();
    }

    /**
     * The List<Owner> to List<OwnerDTO> Mapper
     *
     * @param ownerList the owner list
     *
     * @return the List<OwnerDTO>
     */

    public static List<OwnerDTO> toOwnerDTOListMapper(@NotNull List<Owner> ownerList) {

        return ownerList.stream()
                .map(owner -> OwnerDTO.builder()
                        .idOwner(owner.getIdOwner())
                        .name(owner.getName())
                        .address(owner.getAddress())
                        .phoneNumber(owner.getPhoneNumber())
                        .pets(owner.getPets())
                        .build())
                .toList();
    }

    /**
     * The List<Owner> to List<OwnerSearchDTO> mapper
     *
     * @param searchedOwner the searched owner
     *
     * @return the List<OwnerSearchDTO>
     */

    public static List<OwnerSearchDTO> toOwnerSearchDTOMapper(@NotNull List<Owner> searchedOwner) {

        return searchedOwner.stream()
                .map(owner -> OwnerSearchDTO.builder()
                        .name(owner.getName())
                        .phoneNumber(owner.getPhoneNumber())
                        .build())
                .toList();
    }

    /**
     * The Owner to OwnerSheet Mapper
     *
     * @param owner the owner
     *
     * @return the sheet of the owner
     */

    public static OwnerSheet toOwnerSheetMapper(@NotNull Owner owner) {

        return OwnerSheet.builder()
                .name(owner.getName())
                .address(owner.getAddress())
                .phoneNumber(owner.getPhoneNumber())
                .pets(owner.getPets())
                .build();
    }

    /**
     * The Owner to OwnerUpdateDTO mapper
     *
     * @param updatePet the pet to be updated
     *
     * @return the PetUpdateDTO
     */

    public static OwnerUpdateDTO toOwnerUpdateDTOMapper(@NotNull Owner updatePet) {

        return OwnerUpdateDTO.builder()
                .name(updatePet.getName())
                .address(updatePet.getAddress())
                .phoneNumber(updatePet.getPhoneNumber())
                .pets(updatePet.getPets())
                .build();
    }

}
