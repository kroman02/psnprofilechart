package com.roundtablehold.psnprofilechart.exceptions;

public class ProfilesLimitReachedException extends Exception {
    public ProfilesLimitReachedException(){
        super("Limit of 25 PSN profiles reached. Delete some profiles first.");
    }
}
