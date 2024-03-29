package com.roundtablehold.psnprofilechart.web;


import com.roundtablehold.psnprofilechart.business.PSNProfileService;
import com.roundtablehold.psnprofilechart.business.ProfileScraperService;
import com.roundtablehold.psnprofilechart.data.PSNProfile;
import com.roundtablehold.psnprofilechart.data.ProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PSNProfileController {


   private static int visits;
   private final PSNProfileService psnProfileService;

   public PSNProfileController(PSNProfileService psnProfileService){
       this.psnProfileService = psnProfileService;
   }

   @GetMapping
   public String getProfiles(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllProfiles();
        model.addAttribute("profiles", profiles);
        model.addAttribute("visits", visits);
        return "chart";
   }

    @GetMapping("/platinum")
    public String getProfilesByPlatinum(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedByPlatinumTrophies();
        model.addAttribute("profiles", profiles);
        return "chart";
    }

    @GetMapping("/gold")
    public String getProfilesByGold(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedByGoldTrophies();
        model.addAttribute("profiles", profiles);
        return "chart";
    }

    @GetMapping("/silver")
    public String getProfilesBySilver(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedBySilverTrophies();
        model.addAttribute("profiles", profiles);
        return "chart";
    }

    @GetMapping("/bronze")
    public String getProfilesByBronze(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedByBronzeTrophies();
        model.addAttribute("profiles", profiles);
        return "chart";
    }

    @GetMapping("/level")
    public String getProfilesByLevel(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedByLevel();
        model.addAttribute("profiles", profiles);
        return "chart";
    }

    @GetMapping("/total")
    public String getProfilesByTotal(Model model){
        List<PSNProfile> profiles = psnProfileService.getAllOrderedByTotal();
        model.addAttribute("profiles", profiles);
        return "chart";
    }


}
