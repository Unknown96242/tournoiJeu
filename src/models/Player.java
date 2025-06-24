/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author RT
 */
public class Player {
    private int id;
    private String username;
    private String email;
    private LocalDate birthdate;
    private int ranking;
    private String platform;
    private String status;
    private int teamId;
    private int tournamentId;
    
    // Constructeurs
    public Player() {}

    public Player(int id, String username, String email, LocalDate birthdate, int ranking, String platform,int teamId, String status, int tournamentId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.ranking = ranking;
        this.platform = platform;
        this.status = status;
        this.teamId = teamId;
        this.tournamentId = tournamentId;
    }

    public Player(String username, String email, LocalDate birthdate, String platform, int tournamentId) {
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.platform = platform;
        this.tournamentId = tournamentId;
    }

    public Player(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getTeam() {
        return teamId;
    }

    public void setTeam(int team) {
        this.teamId = team;
    }
    
    // Méthode métier
    public void updateRanking(int pointsGained) {
        this.ranking += pointsGained;
    }
}
