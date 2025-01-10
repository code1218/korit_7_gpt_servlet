package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {

    public void save(User user) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                insert into user_tb 
                values(default, ?, ?, ?, ?)
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate(); //insert, update, delete
            ResultSet keyRs = ps.getGeneratedKeys();
            keyRs.next();
            int userId = keyRs.getInt(1);
            user.setUserId(userId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps);
        }
    }
}









