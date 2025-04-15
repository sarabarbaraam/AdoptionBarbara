package com.sarabarbara.adoption.utils.exceptions.pet;

/**
 * PetValidateException class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 09/04/2025
 */

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(String message) {
        super(message);
    }

}
