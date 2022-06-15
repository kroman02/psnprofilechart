package com.roundtablehold.psnprofilechart.data;

import javax.persistence.*;


@Entity
@Table(name="profile")
public class PSNProfile {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="level")
    private int level;
    @Column(name = "level_progress")
    private String levelProgress;
    @Column(name = "total_trophies")
    private int totalTrophies;
    @Column(name = "platinum_trophies")
    private int platinumTrophies;
    @Column(name = "gold_trophies")
    private int goldTrophies;
    @Column(name = "silver_trophies")
    private int silverTrophies;
    @Column(name = "bronze_trophies")
    private int bronzeTrophies;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelProgress() {
        return levelProgress;
    }

    public void setLevelProgress(String levelProgress) {
        this.levelProgress = levelProgress;
    }

    public int getTotalTrophies() {
        return totalTrophies;
    }

    public void setTotalTrophies(int totalTrophies) {
        this.totalTrophies = totalTrophies;
    }

    public int getPlatinumTrophies() {
        return platinumTrophies;
    }

    public void setPlatinumTrophies(int platinumTrophies) {
        this.platinumTrophies = platinumTrophies;
    }

    public int getGoldTrophies() {
        return goldTrophies;
    }

    public void setGoldTrophies(int goldTrophies) {
        this.goldTrophies = goldTrophies;
    }

    public int getSilverTrophies() {
        return silverTrophies;
    }

    public void setSilverTrophies(int silverTrophies) {
        this.silverTrophies = silverTrophies;
    }

    public int getBronzeTrophies() {
        return bronzeTrophies;
    }

    public void setBronzeTrophies(int bronzeTrophies) {
        this.bronzeTrophies = bronzeTrophies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PSNProfile{" +
                "username='" + username + '\'' +
                ", level=" + level +
                ", levelProgress='" + levelProgress + '\'' +
                ", totalTrophies=" + totalTrophies +
                ", platinumTrophies=" + platinumTrophies +
                ", goldTrophies=" + goldTrophies +
                ", silverTrophies=" + silverTrophies +
                ", bronzeTrophies=" + bronzeTrophies +
                '}';
    }
}

