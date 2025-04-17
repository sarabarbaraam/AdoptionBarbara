package com.sarabarbara.adoption.repositories;


import com.sarabarbara.adoption.models.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * Finds the owner by the name, phone number or address
     *
     * @param name        the name of the owner
     * @param address     the address of the owner
     * @param phoneNumber the phone number of the owner
     * @param pageable    the pageable
     *
     * @return the searched owner
     */

    @Query("SELECT o FROM Owner o " +
            "WHERE " +
            "(:name IS NULL OR LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:address IS NULL OR :address = '' OR LOWER(o.address) LIKE LOWER(CONCAT('%', :address, '%'))) AND " +
            "(:phoneNumber IS NULL OR :phoneNumber = '' OR LOWER(o.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%')))")
    Page<Owner> searchOwners(@Param("name") String name,
                             @Param("address") String address,
                             @Param("phoneNumber") String phoneNumber,
                             Pageable pageable);

    /**
     * Finds the owner by the phone number
     *
     * @param phoneNumber the phone number of the owner
     *
     * @return the owner with the phone number
     */

    @NotNull Optional<Owner> findByPhoneNumber(@NotNull String phoneNumber);

}