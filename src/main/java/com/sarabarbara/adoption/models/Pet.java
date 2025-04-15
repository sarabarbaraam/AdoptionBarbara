package com.sarabarbara.adoption.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Pet class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 03/04/2025
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    /**
     * The serialVersionUID
     */

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The idPet
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;

    /**
     * The name
     */

    @NonNull
    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String name;

    /**
     * The birthDate
     */

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "The birth date must be in the past")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * The breed
     */

    @NonNull
    @Size(min = 3, max = 45, message = "The name must be between 3 and 45 characters")
    private String breed;

    /**
     * The weight
     */

    @NonNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "1000.0")
    private Float weight;

    /**
     * The hasChip
     */

    @NonNull
    @Column(name = "has_chip")
    private Boolean hasChip;

    /**
     * The isAdopted
     */

    @NonNull
    @Column(name = "is_adopted")
    private Boolean isAdopted;

    /**
     * The owner
     */

    @ManyToOne
    @JoinColumn(name = "id_owner")
    @JsonBackReference
    private Owner owner;

    /**
     * The photoUrl
     */

    @NonNull
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * The Equals
     *
     * @param o the o
     *
     * @return the equals
     */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pet pet)) return false;
        return Objects.equals(getIdPet(), pet.getIdPet()) && Objects.equals(getName(), pet.getName())
                && Objects.equals(getBirthDate(), pet.getBirthDate()) && Objects.equals(getBreed(), pet.getBreed())
                && Objects.equals(getWeight(), pet.getWeight()) && Objects.equals(getHasChip(), pet.getHasChip())
                && Objects.equals(getIsAdopted(), pet.getIsAdopted()) && Objects.equals(getOwner(), pet.getOwner())
                && Objects.equals(getPhotoUrl(), pet.getPhotoUrl());
    }

    /**
     * The HashCode
     *
     * @return the hashCode
     */

    @Override
    public int hashCode() {
        return Objects.hash(getIdPet(), getName(), getBirthDate(), getBreed(), getWeight(), getHasChip(),
                getIsAdopted(), getOwner(), getPhotoUrl());
    }

    /**
     * The toString
     *
     * @return the string
     */

    @Override
    public String toString() {
        return "Pet{" +
                "idPet=" + idPet +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", hasChip=" + hasChip +
                ", isAdopted=" + isAdopted +
                ", owner=" + owner +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

}
