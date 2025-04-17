package com.sarabarbara.adoption.dto.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * YoungestPetDTO class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 17/04/2025
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class YoungestPetDTO {

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

}
