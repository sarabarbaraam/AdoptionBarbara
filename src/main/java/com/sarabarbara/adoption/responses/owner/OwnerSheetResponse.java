package com.sarabarbara.adoption.responses.owner;

import com.sarabarbara.adoption.sheets.OwnerSheet;
import lombok.*;

/**
 * OwnerSheetResponse class
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
public class OwnerSheetResponse {

    /**
     * The success
     */

    private boolean success;

    /**
     * The ownerSheet
     */

    private OwnerSheet ownerSheet;

    /**
     * The message
     */

    private String message;

}
