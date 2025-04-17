package com.sarabarbara.adoption.dto.owner;

import lombok.*;

/**
 * OwnerSearchDTO class
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
public class OwnerSearchDTO {

    /**
     * The name
     */

    @NonNull
    private String name;

    /**
     * The phoneNumber
     */

    @NonNull
    private String phoneNumber;

}
