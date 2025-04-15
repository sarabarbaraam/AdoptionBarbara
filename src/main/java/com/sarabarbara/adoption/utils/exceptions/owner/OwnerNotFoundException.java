package com.sarabarbara.adoption.utils.exceptions.owner;

/**
 * PetValidateException class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 09/04/2025
 */

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message) {
        super(message);
    }

}
