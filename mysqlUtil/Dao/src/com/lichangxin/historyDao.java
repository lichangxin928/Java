package com.lichangxin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class historyDao {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public historyDao() {
    }

    // 增

    /**
     *
     * @param email
     * @param movie
     * @param amount
     * @param date
     * @return
     */
    public int Insert(String email,String movie,int amount,String date){
        con = Util.getConnection();
        try {
            ps = con.prepareStatement("insert into history value (?,?,?,?)");
            ps.setString(1,email);
            ps.setString(2,movie);
            ps.setInt(3,amount);
            ps.setString(4,date);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util.close(con,ps,rs);
        }
        return 0;
    }
    // 删
    public int Delete(String email){
        con = Util.getConnection();
        try{
            ps = con.prepareStatement("delete from history where email = ?");
            ps.setString(1,email);
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Util.close(con,ps,rs);
        }
        return 0;
    }

    // 改
    public int Update(String email,String movie,int amount,String date){
        con = Util.getConnection();
        try{
            ps = con.prepareStatement("update history set movie = ?,amount = ?,date=? where email = ?");
            ps.setString(1,movie);
            ps.setInt(2,amount);
            ps.setString(3,date);
            ps.setString(4,email);
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Util.close(con,ps,rs);
        }
        return 0;
    }
    // 查
    public ResultSet Select(){
        con = Util.getConnection();
        try{
            ps = con.prepareStatement("select * from history");
            return ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
//            Util.close(con,ps,rs);
        }
        return null;
    }
}
