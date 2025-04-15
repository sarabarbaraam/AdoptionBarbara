package com.sarabarbara.adoption.repositories;


import com.sarabarbara.adoption.models.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * OwnerRepository class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 03/04/2025
 */

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    /**
     * Find all the owner
     *
     * @param pageable the pageable
     *
     * @return all the owner
     */

    @NotNull Page<Owner> findAll(@NotNull Pageable pageable);

    /**
     * Finds the owner by the phone number
     *
     * @param phoneNumber the phone number of the owner
     *
     * @return the owner with the phone number
     */

    @NotNull Optional<Owner> findByPhoneNumber(@NotNull String phoneNumber);

}