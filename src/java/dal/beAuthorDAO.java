/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beAuthor;

/**
 *
 * @author Admin
 */
public class beAuthorDAO extends DBContext{
    public ArrayList<beAuthor> getAllNonActiveReq(){
        String sql = "SELECT * FROM beAuthor WHERE isActive = 0;";
        ArrayList<beAuthor> bealist = new ArrayList<>();
        beAuthor bea;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                bea = new beAuthor(rs.getInt("authorId"), rs.getInt("userId"), rs.getString("authorName"), rs.getString("introduction"), rs.getInt("isActive"));
                bealist.add(bea);
            }
            return bealist;
        } catch (SQLException ex) {
            Logger.getLogger(beAuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int isActiveOn(int uid){
        String sql = "UPDATE beAuthor\n"
                + "SET isActive = 1\n"
                + "WHERE userId = "+uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);;
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int delRequest(int uid){
        String sql = "DELETE FROM beAuthor WHERE userId = "+uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);;
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
