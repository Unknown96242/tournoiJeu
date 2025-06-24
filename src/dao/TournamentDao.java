package dao;

import models.Tournament;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TournamentDao {

    private static final String SQL_SELECT = "SELECT * FROM tournaments";
    private static final String SQL_INSERT = 
        "INSERT INTO tournaments (email, password, name, game, type, is_online, max_participant, status, prize_pool, description, start_date, end_date) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_CONNEXION = "SELECT * FROM tournaments WHERE email = ?";

    public static Tournament createTournament(Tournament tournament) throws SQLException, ClassNotFoundException {
        long idInsert = DatabaseService.executeInsertWithGeneratedKey(
            SQL_INSERT,
            tournament.getEmail(),
            tournament.getPassword(),
            tournament.getName(),
            tournament.getGame(),
            tournament.getType(),
            tournament.isIsOnline(),
            tournament.getMaxParticipant(),
            tournament.getStatus(),
            tournament.getPrizePool(),
            tournament.getDescription(),
            tournament.getStartDate(),
            tournament.getEndDate()
        );
         tournament.setId((int) idInsert);

        return tournament;
    }

    public static Tournament getCurrentTournament(String email) throws SQLException, ClassNotFoundException {
        Tournament currentTournament = null;
        try (ResultSet rs = DatabaseService.executeQuery(SQL_CONNEXION, email)) {
            if (rs.next()) {
                currentTournament = convertResultSetTournament(rs);
            }
        }
        return currentTournament;
    }

    public static Tournament convertResultSetTournament(ResultSet rs) throws SQLException {
        return new Tournament(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("game"),
            rs.getString("type"),
            rs.getBoolean("is_online"),
            rs.getInt("max_participant"),
            rs.getString("status"),
            rs.getDouble("prize_pool"),
            rs.getString("description"),
            rs.getObject("start_date", LocalDate.class),
            rs.getObject("end_date", LocalDate.class)
        );
    }
}