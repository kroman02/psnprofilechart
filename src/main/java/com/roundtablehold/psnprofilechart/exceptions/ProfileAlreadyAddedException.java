package com.roundtablehold.psnprofilechart.exceptions;

public class ProfileAlreadyAddedException extends Exception {

    public ProfileAlreadyAddedException(String username){
        super("User "+username+" is already on the chart.");
    }
}
