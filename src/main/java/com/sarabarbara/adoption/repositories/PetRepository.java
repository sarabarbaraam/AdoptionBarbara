package com.sarabarbara.adoption.repositories;

import com.sarabarbara.adoption.models.Owner;
import com.sarabarbara.adoption.models.Pet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * PetRepository class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 03/04/2025
 */

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    /**
     * Find all the pets or owner
     *
     * @param pageable the pageable
     *
     * @return all the pets or owner
     */

    @NotNull Page<Pet> findAll(@NotNull Pageable pageable);

    /**
     * Find the pets by the urlPhoto
     *
     * @param url the url
     *
     * @return the pets with the url photo assigned
     */

    @NotNull Optional<Pet> findByPhotoUrl(@NotNull String url);

    /**
     * Find the pets or owner by the id
     *
     * @param id the id of the pets or owner
     *
     * @return the pets or owner searched
     */

    @NotNull Optional<Pet> findById(@NotNull Long id);

    /**
     * Searches a pet by an only or many parameters
     *
     * @param name             the name of the pet
     * @param birthDate        the birthdate of the pet
     * @param breed            the breed of the pet
     * @param weight           the weight of the pet
     * @param hasChip          if the pet has chip
     * @param isAdopted        if the pet is adopted
     * @param phoneNumberOwner the phone number of the owner of the pet
     * @param photoUrl         the url of the photo of the pet
     * @param pageable         the pageable
     *
     * @return the pet searched
     */

    @Query("SELECT p FROM Pet p " +
            "LEFT JOIN p.owner o " +  // LEFT JOIN para que las mascotas sin dueño también se incluyan si es necesario
            "WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:birthDate IS NULL OR FUNCTION('DATE', p.birthDate) = :birthDate) AND " +
            "(:breed IS NULL OR LOWER(p.breed) LIKE LOWER(CONCAT('%', :breed, '%'))) AND " +
            "(:weight IS NULL OR p.weight = :weight) AND " +
            "(:hasChip IS NULL OR p.hasChip = :hasChip) AND " +
            "(:isAdopted IS NULL OR p.isAdopted = :isAdopted) AND " +
            "(:phoneNumberOwner IS NULL OR :phoneNumberOwner = '' OR LOWER(o.phoneNumber) LIKE LOWER(CONCAT('%', " +
            ":phoneNumberOwner, '%'))) AND " +
            "(:photoUrl IS NULL OR LOWER(p.photoUrl) LIKE LOWER(CONCAT('%', :photoUrl, '%'))) ")
    Page<Pet> searchPets(@Param("name") String name,
                         @Param("birthDate") LocalDate birthDate,
                         @Param("breed") String breed,
                         @Param("weight") Float weight,
                         @Param("hasChip") Boolean hasChip,
                         @Param("isAdopted") Boolean isAdopted,
                         @Param("phoneNumberOwner") String phoneNumberOwner,
                         @Param("photoUrl") String photoUrl,
                         @NotNull Pageable pageable);


    @NotNull Optional<Pet> findByNameAndOwner(String petName, Owner owner);

    /**
     * Filters the 20 youngest pets
     *
     * @param pageable tha pageable
     *
     * @return the 20 youngest pets
     */

    @Query("SELECT p FROM Pet p ORDER BY p.birthDate DESC")
    List<Pet> findTopYoungestPets(Pageable pageable);

}
