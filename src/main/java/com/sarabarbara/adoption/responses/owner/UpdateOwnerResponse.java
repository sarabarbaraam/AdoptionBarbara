package com.sarabarbara.adoption.responses.owner;

import com.sarabarbara.adoption.dto.owner.OwnerUpdateDTO;
import lombok.*;

/**
 * UpdateOwnerResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 15/04/2025
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class UpdateOwnerResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The petUpdate
     */

    private OwnerUpdateDTO ownerUpdate;

    /**
     * The message
     */

    private String message;

}
