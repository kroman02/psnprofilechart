package com.roundtablehold.psnprofilechart.web;

import com.roundtablehold.psnprofilechart.business.PSNProfileService;
import com.roundtablehold.psnprofilechart.business.ProfileScraperService;
import com.roundtablehold.psnprofilechart.data.PSNProfile;
import com.roundtablehold.psnprofilechart.exceptions.ProfileAlreadyAddedException;
import com.roundtablehold.psnprofilechart.exceptions.ProfileDoesNotExistException;
import com.roundtablehold.psnprofilechart.exceptions.ProfilesLimitReachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class PSNProfileRestController {

    private final PSNProfileService psnProfileService;
    private final ProfileScraperService profileScraperService;

    @Autowired
    PSNProfileRestController(PSNProfileService psnProfileService, ProfileScraperService profileScraperService){
        this.psnProfileService = psnProfileService;
        this.profileScraperService = profileScraperService;
    }

    @GetMapping
    public List<PSNProfile> getAll(){
        return psnProfileService.getAllProfiles();
    }

    @GetMapping (path = "/{id}")
    public PSNProfile getOne(@PathVariable(value ="id") Long id){
        return psnProfileService.getOne(id);
    }

    @PostMapping("/add")
    public PSNProfile addProfile(@RequestParam String username) throws Exception {

        if(psnProfileService.profileExistsInTable(username)) {
            throw new ProfileAlreadyAddedException(username);
        }else if(psnProfileService.limitReached()){
            throw new ProfilesLimitReachedException();
        }
        PSNProfile profile = psnProfileService.addProfile(username);
        if(profile == null){
            throw new ProfileDoesNotExistException(username);
        }else{
            return profile;
        }
    }

    @GetMapping("/getPSNProfile")
    public PSNProfile getPSNProfile(@RequestParam String username) throws Exception {
        PSNProfile profile = profileScraperService.getPSNProfile(username);
        if(profile == null){
            throw new ProfileDoesNotExistException(username);
        }else{
            return profile;
        }
    }

    @GetMapping("/getall")
    public List<PSNProfile> getAllProfiles() {
        return psnProfileService.getAllProfiles();
    }

    @DeleteMapping("/remove")
    public void removePSNProfile(@RequestParam String username) throws IOException, ProfileDoesNotExistException {
            psnProfileService.removeProfile(username);
    }

    @PatchMapping("/updateall")
    public void updateAllProfiles(){
        psnProfileService.updateAllProfiles();
    }


    @ExceptionHandler(ProfileAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String profileAlreadyExistsHandler(ProfileAlreadyAddedException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(ProfileDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String profileNotFoundHandler(ProfileDoesNotExistException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(ProfilesLimitReachedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String profilesLimitReachedHandler(ProfilesLimitReachedException ex){
        return ex.getMessage();
    }
}
