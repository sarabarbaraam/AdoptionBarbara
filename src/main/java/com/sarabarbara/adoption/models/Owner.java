package com.sarabarbara.adoption.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Owner class
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
@Table(name = "owner")
public class Owner implements Serializable {

    /**
     * The serialVersionUID
     */

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The idOwner
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Long idOwner;

    /**
     * The name
     */

    @NonNull
    private String name;

    /**
     * The address
     */

    @NonNull
    private String address;

    /**
     * The phoneNumber
     */

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * The pets
     */

    @NonNull
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pet> pets;

    /**
     * The ToString
     *
     * @return the toString
     */

    @Override
    public String toString() {
        return "Owner{" +
                "idOwner=" + idOwner +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pets=" + pets +
                '}';
    }

    /**
     * The Equals
     *
     * @param o the o
     *
     * @return the equals
     */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(getIdOwner(), owner.getIdOwner()) && Objects.equals(getName(), owner.getName())
                && Objects.equals(getAddress(), owner.getAddress())
                && Objects.equals(getPhoneNumber(), owner.getPhoneNumber()) && Objects.equals(getPets(), owner.getPets());
    }

    /**
     * The HashCode
     *
     * @return the hashCode
     */

    @Override
    public int hashCode() {
        return Objects.hash(getIdOwner(), getName(), getAddress(), getPhoneNumber(), getPets());
    }

}
