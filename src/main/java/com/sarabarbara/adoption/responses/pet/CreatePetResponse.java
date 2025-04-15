package com.sarabarbara.adoption.responses.pet;

import com.sarabarbara.adoption.dto.pet.PetCreateDTO;
import lombok.*;

/**
 * CreatePetResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 08/04/2025
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class CreatePetResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The petCreate
     */

    private PetCreateDTO petCreate;

    /**
     * The message
     */

    private String message;

}
