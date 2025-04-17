package com.sarabarbara.adoption.dto.owner;

import com.sarabarbara.adoption.models.Pet;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

/**
 * OwnerDTO class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OwnerDTO {

    /**
     * The idOwner
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOwner;

    /**
     * The name
     */

    @NonNull
    private String name;

    /**
     * The address
     */

    @NonNull
    private String address;

    /**
     * The phoneNumber
     */

    @NonNull
    private String phoneNumber;

    /**
     * The pets
     */

    private List<Pet> pets;

}
