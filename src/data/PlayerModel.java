
package data;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Game;
/**
 *
 * @author oni
 */
public class PlayerModel {
    private String username;
    private int score;
    private int x,y,level,hp,power;

    public static PlayerModel loadPlayerInfo() {
        ResultSet rs;
        String sql = "CALL getPlayerInfo(?)";
        try {
            PreparedStatement ps = Connect.getConnection().prepareCall(sql);
            ps.setInt(1, Game.PLAYER_ID);
            rs = ps.executeQuery();
            PlayerModel result = new PlayerModel();
            if (rs.next()) {
                result.setUsername(rs.getString("username"));
                result.setScore(rs.getInt("score"));
                result.setX(rs.getInt("current_x"));
                result.setY(rs.getInt("current_y"));
                result.setLevel(rs.getInt("current_lvl"));
                result.setHp(rs.getInt("hp"));
                result.setPower(rs.getInt("power"));
            }
            System.out.println(result.getUsername());
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Return null if an error occurred
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public PlayerModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void saveScore() throws SQLException {
        String sql = "INSERT INTO `high_score`(playerId,lvl,score) VALUES(?,?,?)";
        PreparedStatement ps = Connect.getConnection().prepareStatement(sql);
        ps.setInt(1, Game.PLAYER_ID);
        ps.setInt(2,level);
        ps.setInt(3, score);
        ps.executeUpdate();
    }
    
}
