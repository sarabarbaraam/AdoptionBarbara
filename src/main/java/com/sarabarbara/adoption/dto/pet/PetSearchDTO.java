package com.sarabarbara.adoption.dto.pet;

import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * PetSearchDTO class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 10/04/2025
 */

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PetSearchDTO {

    /**
     * The name
     */

    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String name;

    /**
     * The breed
     */

    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String breed;

    /**
     * The isAdopted
     */

    private Boolean isAdopted;

}
