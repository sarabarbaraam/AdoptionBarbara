package com.sarabarbara.adoption.dto.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sarabarbara.adoption.models.Owner;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * PetCreateDTO class
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
public class PetCreateDTO {

    /**
     * The name
     */

    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String name;

    /**
     * The birthDate
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "The birth date must be in the past")
    private LocalDate birthDate;

    /**
     * The breed
     */

    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String breed;

    /**
     * The weight
     */

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "1000.0")
    private Float weight;

    /**
     * The hasChip
     */

    private Boolean hasChip;

    /**
     * The isAdopted
     */

    private Boolean isAdopted;

    /**
     * The owner
     */

    private Owner owner;

    /**
     * The photoUrl
     */

    private String photoUrl;

}
