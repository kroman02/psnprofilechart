package com.roundtablehold.psnprofilechart.business;

import com.roundtablehold.psnprofilechart.data.PSNProfile;
import com.roundtablehold.psnprofilechart.data.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PSNProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileScraperService profileScraperService;

    @Autowired
    public PSNProfileService(ProfileRepository profileRepository, ProfileScraperService profileScraperService){
        this.profileRepository = profileRepository;
        this.profileScraperService = profileScraperService;
    }


    public List<PSNProfile> getAllOrderedByPlatinumTrophies(){
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getPlatinumTrophies).reversed())
                .collect(Collectors.toList());
    }
    public List<PSNProfile> getAllOrderedByGoldTrophies(){
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getGoldTrophies).reversed())
                .collect(Collectors.toList());
    }
    public List<PSNProfile> getAllOrderedBySilverTrophies(){
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getSilverTrophies).reversed())
                .collect(Collectors.toList());
    }
    public List<PSNProfile> getAllOrderedByBronzeTrophies(){
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getBronzeTrophies).reversed())
                .collect(Collectors.toList());
    }
    public List<PSNProfile> getAllOrderedByLevel(){
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getLevel).reversed())
                .collect(Collectors.toList());
    }

    public List<PSNProfile> getAllOrderedByTotal() {
        return profileRepository.findAll().stream()
                .sorted(Comparator.comparingInt(PSNProfile::getTotalTrophies).reversed())
                .collect(Collectors.toList());
    }


    public List<PSNProfile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public PSNProfile getOne(Long id){
        return profileRepository.findById(id).get();
    }

    public PSNProfile addProfile(String username) throws IOException {
        PSNProfile profile = profileScraperService.getPSNProfile(username);
        if(profile == null){
            return null;
        }else{
            return profileRepository.save(profile);
        }

    }


}
