package com.sarabarbara.adoption.responses.pet;

import com.sarabarbara.adoption.sheets.PetSheet;
import lombok.*;

/**
 * PetSheetResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 10/04/2025
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PetSheetResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The petSheet
     */

    private PetSheet petSheet;

    /**
     * The message
     */

    private String message;
}
