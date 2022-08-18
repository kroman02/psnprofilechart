package com.roundtablehold.psnprofilechart.business;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.roundtablehold.psnprofilechart.data.PSNProfile;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProfileScraperService {


    private final WebClient webClient;
    private final String URL = "https://psnprofiles.com/";

    @Autowired
    public ProfileScraperService(WebClient webClient){
        this.webClient = webClient;
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    private boolean userProfileExists(HtmlPage page) {
        String title = page.getTitleText();
        return title.contains("'");
    }

    public PSNProfile getPSNProfile(String username) throws IOException {
        if(username.length() < 1)return null;
        HtmlPage page = webClient.getPage(this.URL + username);
        if(!userProfileExists(page)){
            return null;
        }
        PSNProfile PSNProfile = new PSNProfile();

        List<HtmlElement> items = page.getByXPath("//*[@class='profile-bar']");
        // Retrieving main element containing profile information
        HtmlElement profileBar = items.get(0);

        // Retrieving nickname
        HtmlElement nicknameElement = profileBar.getFirstByXPath("//span[@class='username']");
        String PSNusername = nicknameElement.getTextContent();

        //Retrieving progress for current level
        HtmlElement progressElement = profileBar.getFirstByXPath("//*[@id=\"bar-level\"]/div/div");
        DomAttr progressAtt = progressElement.getAttributeNode("style");
        String a = progressAtt.getValue();
        Pattern p = Pattern.compile("\\d%");
        Matcher m = p.matcher(a);

        String progress = "";
        if(m.find()){
            progress = m.group();
        }

        // Retrieving html li elements containing user leve and trophies [total, platinum, gold, silver, bronze]
        List<HtmlElement> trophies = profileBar.getByXPath(".//li");
        String levelString = trophies.get(0).getTextContent().trim().replace(",", "");
        int level = 0;
        if(levelString.length() > 0){
            level = Integer.parseInt(levelString);
        }else{
            PSNusername += "[PRIVATE]";
        }
        int totalTrophies = Integer.parseInt(trophies.get(1).getTextContent().trim().replace(",", ""));
        int platinumTrophies = Integer.parseInt(trophies.get(2).getTextContent().trim().replace(",", ""));
        int goldTrophies = Integer.parseInt(trophies.get(3).getTextContent().trim().replace(",", ""));
        int silverTrophies = Integer.parseInt(trophies.get(4).getTextContent().trim().replace(",", ""));
        int bronzeTrophies = Integer.parseInt(trophies.get(5).getTextContent().trim().replace(",", ""));

        PSNProfile.setUsername(PSNusername);
        PSNProfile.setLevel(level);
        PSNProfile.setLevelProgress(progress);
        PSNProfile.setTotalTrophies(totalTrophies);
        PSNProfile.setPlatinumTrophies(platinumTrophies);
        PSNProfile.setGoldTrophies(goldTrophies);
        PSNProfile.setSilverTrophies(silverTrophies);
        PSNProfile.setBronzeTrophies(bronzeTrophies);

        return PSNProfile;
    }




}





