package com.roundtablehold.psnprofilechart.exceptions;

public class ProfileDoesNotExistException extends Exception {

    public ProfileDoesNotExistException(String username){
        super("Could not find user "+username);
    }
}
