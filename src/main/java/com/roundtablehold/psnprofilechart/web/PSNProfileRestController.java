package com.roundtablehold.psnprofilechart.web;

import com.roundtablehold.psnprofilechart.business.PSNProfileService;
import com.roundtablehold.psnprofilechart.data.PSNProfile;
import com.roundtablehold.psnprofilechart.exceptions.ProfileDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("roundtablehold/api/v1/profiles")
public class PSNProfileRestController {

    private final PSNProfileService psnProfileService;

    @Autowired
    PSNProfileRestController(PSNProfileService psnProfileService){
        this.psnProfileService = psnProfileService;
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
        PSNProfile profile = psnProfileService.addProfile(username);
        if(profile == null){
            throw new ProfileDoesNotExistException(username);
        }else{
            return profile;
        }
    }

    @GetMapping("/porcodio")
    public String porcodio(){
        return "dio merda";
    }

    @ExceptionHandler(ProfileDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String profileNotFoundHandler(ProfileDoesNotExistException ex){
        return ex.getMessage();
    }


}
