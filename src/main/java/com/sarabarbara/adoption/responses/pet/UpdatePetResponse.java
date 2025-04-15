package com.sarabarbara.adoption.responses.pet;

import com.sarabarbara.adoption.dto.pet.PetUpdateDTO;
import lombok.*;

/**
 * UpdatePetResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 10/04/2025
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class UpdatePetResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The petUpdate
     */

    private PetUpdateDTO petUpdate;

    /**
     * The message
     */

    private String message;
}
