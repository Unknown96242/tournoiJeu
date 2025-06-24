package dao;

import models.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    private static final String SQL_SELECT = "SELECT * FROM players WHERE tournament_id = ?";
    private static final String SQL_INSERT = "INSERT INTO players (username, email, birthdate, platform, tournament_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE players SET username = ?, email = ?, birthdate = ?, ranking = ?, platform = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM players WHERE id = ?";

    public static Player addPlayerToTournament(Player player) throws SQLException, ClassNotFoundException {
        long idInsert = DatabaseService.executeInsertWithGeneratedKey(
                SQL_INSERT,
                player.getUsername(),
                player.getEmail(),
                player.getBirthdate(), // doit être un java.sql.Date ou LocalDate compatible avec ton DatabaseService
                player.getPlatform(),
                player.getTournamentId()
        );
        player.setId((int) idInsert); // Met à jour l'id du joueur si tu as un setter
        return player;
    }

    public static List<Player> getAllPlayer(int tournamentId) throws SQLException, ClassNotFoundException {
        List<Player> listPlayer = new ArrayList<>();
        try (ResultSet rs = DatabaseService.executeQuery(SQL_SELECT, tournamentId)) {
            while (rs.next()) {
                Player player = convertResultSetToPlayer(rs);
                listPlayer.add(player);
            }
        }
        return listPlayer;
    }

    public static Player convertResultSetToPlayer(ResultSet rs) throws SQLException {
        return new Player(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getObject("birthdate", LocalDate.class), // OK pour PostgreSQL
                rs.getInt("ranking"),
                rs.getString("platform"),
                rs.getInt("team_id"),
                rs.getString("status"),
                rs.getInt("tournament_id")
        );
    }
    
    
}