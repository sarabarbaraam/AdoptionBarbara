package com.sarabarbara.adoption.dto.owner;

import com.sarabarbara.adoption.models.Pet;
import lombok.*;

import java.util.List;

/**
 * OwnerCreateDTO class
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
public class OwnerCreateDTO {

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
