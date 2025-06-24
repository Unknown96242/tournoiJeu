/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.time.LocalDate;
import java.util.*;
import models.Match;
import models.Player;

/**
 *
 * @author RT
 */
public class MatchMacking {
    public List<Match> genererPremierTour(List<Player> players, LocalDate matchTime, int tournamentId){
        List<Player> copie = new ArrayList<>(players);
        Collections.shuffle(copie);
        
        List<Match> matchs = new ArrayList<>();
        int round = 1;
        for(int i = 0; i< copie.size(); i+=2){
            
            double player1Id = copie.get(i).getId();
            double player2Id = (i+1< copie.size())? copie.get(i+1).getId():null;
            
            matchs.add(new Match(matchTime,round, player1Id, player2Id, tournamentId ));

        }
        
        return matchs;

    };
}
