/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author RT
 */
public class Match {
    
    private double id;
    private LocalDate matchTime;
    private int round;
    private double player1Id;
    private double player2Id;
    private String status;
    private int tournamentId;
    private int winnerId;

    public Match(double id, LocalDate matchTime, int round, double player1Id, double player2Id, String status, int tournamentId, int winnerId) {
        this.id = id;
        this.matchTime = matchTime;
        this.round = round;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.status = status;
        this.tournamentId = tournamentId;
        this.winnerId = winnerId;
    }

    public Match(LocalDate matchTime, int round, double player1Id, double player2Id, int tournamentId) {
        this.matchTime = matchTime;
        this.round = round;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.tournamentId = tournamentId;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public LocalDate getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDate matchTime) {
        this.matchTime = matchTime;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public double getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(double player1Id) {
        this.player1Id = player1Id;
    }

    public double getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(double player2Id) {
        this.player2Id = player2Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTournamentId() {
        return tournamentId;
    }

//    public void setTournamentId(int tournamentId) {
//        this.tournamentId = tournamentId;
//    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    
    
}
