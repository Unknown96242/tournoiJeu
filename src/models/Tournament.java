package models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author RT
 */
public class Tournament {
    
    private int id;
    private String email;
    private String password;
    private String name;
    private String game;
    private String type; // Exemple : "Elimination directe", "Round Robin"
    private boolean isOnline;
    private int maxParticipant;
    private String status; // Exemple : "Prévu", "En cours", "Terminé"
    private double prizePool;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

   
    
    private List<Player> registeredPlayers = new ArrayList<>();
    private List<Team> registeredTeams = new ArrayList<>();

    

    public Tournament(String email, String password, String name, String game, String type, boolean isOnline, int maxParticipant, String status, double prizePool, String description, LocalDate startDate, LocalDate endDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.game = game;
        this.type = type;
        this.isOnline = isOnline;
        this.maxParticipant = maxParticipant;
        this.status = status;
        this.prizePool = prizePool;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Tournament(int id, String email, String password, String name, String game, String type, boolean isOnline, int maxParticipant, String status, double prizePool, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.game = game;
        this.type = type;
        this.isOnline = isOnline;
        this.maxParticipant = maxParticipant;
        this.status = status;
        this.prizePool = prizePool;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public boolean registerPlayer(Player player) {
        if (!"registration".equalsIgnoreCase(this.status)) {
            System.out.println("Inscriptions closes pour ce tournoi.");
            return false;
        }
        if (registeredPlayers.contains(player)) {
            System.out.println("Le joueur est déjà inscrit.");
            return false;
        }
        registeredPlayers.add(player);
        System.out.println("Joueur " + player.getUsername() + " inscrit avec succès au tournoi " + name);
        return true;
    }

    public boolean registerTeam(Team team) {
        if (!"registration".equalsIgnoreCase(this.status)) {
            System.out.println("Inscriptions closes pour ce tournoi.");
            return false;
        }
        if (registeredTeams.contains(team)) {
            System.out.println("L'équipe est déjà inscrite.");
            return false;
        }
        registeredTeams.add(team);
        System.out.println("Équipe " + team.getName() + " inscrite avec succès au tournoi " + name);
        return true;
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public List<Team> getRegisteredTeams() {
        return registeredTeams;
    }
    
    public void startTournament() {
        if ("registration".equals(this.status)) {
            this.status = "ongoing";
            System.out.println("Le tournoi " + name + " commence.");
        }
    }

    public void endTournament() {
        if ("ongoing".equals(this.status)) {
            this.status = "finished";
            System.out.println("Le tournoi " + name + " est terminé.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(double prizePool) {
        this.prizePool = prizePool;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    
}


