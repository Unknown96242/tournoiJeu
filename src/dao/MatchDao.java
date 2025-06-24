/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Match;

/**
 *
 * @author RT
 */
public class MatchDao {
    private static final String SQL_SELECT ="SELECT * FROM matches WHERE tournament_id = ?";
    private static final String SQL_INSERT ="INSERT INTO matches (match_time, round, player1_id, player2_id, tournament_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE matches SET match_time =?, status =?, winner_id = ? WHERE id = ?";
    
    private static List<Match> getAllMatchOfTournament( int tournamentId)throws SQLException, ClassNotFoundException{
        
        List<Match> listMatch = new ArrayList<>();
        
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT,tournamentId);
        
        while(rs.next()){
            Match match = convertResultSetToMatch(rs);
            listMatch.add(match);
        }
        return listMatch;
    
    }

    private static Match convertResultSetToMatch(ResultSet rs) throws SQLException, ClassNotFoundException{
        return new Match(
                rs.getDouble("id"), 
                rs.getObject("match_time", LocalDate.class),
                rs.getInt("round"),
                rs.getDouble("player1_id"),
                rs.getDouble("player2.id"),
                rs.getString("status"),
                rs.getInt("tournament_id"),
                rs.getInt("winner_id")
        );
    }
    
    public static Match addMatchInTournament(Match match) throws SQLException, ClassNotFoundException{
        long idInsert = DatabaseService.executeInsertWithGeneratedKey(SQL_INSERT, 
                match.getMatchTime(),
                match.getRound(),
                match.getPlayer1Id(),
                match.getPlayer2Id(),
                match.getTournamentId()
                
                );
        match.setId((double) idInsert);
        return match; 
    }
                
}
