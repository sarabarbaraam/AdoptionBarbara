package com.sarabarbara.adoption.sheets;

import com.sarabarbara.adoption.models.Pet;
import lombok.*;

import java.util.List;

/**
 * OwnerSheet class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OwnerSheet {

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

    @NonNull
    private List<Pet> pets;

}
