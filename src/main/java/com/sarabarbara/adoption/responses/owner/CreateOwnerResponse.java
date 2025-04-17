package com.sarabarbara.adoption.responses.owner;

import com.sarabarbara.adoption.dto.owner.OwnerCreateDTO;
import lombok.*;

/**
 * CreateOwnerResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class CreateOwnerResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The petCreate
     */

    private OwnerCreateDTO ownerCreate;

    /**
     * The message
     */

    private String message;

}
