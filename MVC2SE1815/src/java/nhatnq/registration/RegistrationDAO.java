/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatnq.registration;

import nhatnq.util.DBhelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO implements Serializable {

    //Cung cấp các Phương thức truy cập database
//    public boolean checkLogin(String username, String password)
//            throws SQLException, ClassNotFoundException {
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        RegistrationDTO result = null;
        Connection con = null;//khai bao, declare
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get connection object(check valid connection
            con = DBhelper.getConnection();
            if (con != null) {
                //2. write SQL string
                String sql = "SELECT lastName, isAdmin "
                        + "FROM Registration "
                        + "WHERE username=? "
                        + "AND password=?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    //5.1 Model get data from resultSet
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 Set data to DTO property
                    result = new RegistrationDTO(
                            username, password, fullName, role);
                }//username and password are existed
            }//connection is available
        } finally {//close anyway
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    
    
    public void searchLastName(String searchValue) throws ClassNotFoundException, SQLException{
        boolean result = false;
        Connection con = null;//khai bao, declare
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get connection object(check valid connection
            con = DBhelper.getConnection();
            if (con != null) {
                //2. write SQL string
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastName Like ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + searchValue + "%");
                //4. Excute query
                rs = stm.executeQuery();
                //5. process result
                while(rs.next()) {
                    //5.1 Model get data from resultset
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 Model sets data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }//traverse resultset
            }//connection is available
        } finally {//close anyway
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;//khai bao, declare
        PreparedStatement stm = null;
        

        try {
            //1. get connection object(check valid connection
            con = DBhelper.getConnection();
            if (con != null) {
                //2. write SQL string
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Excute query
                int affectRows = stm.executeUpdate();
                //5. process result
               if (affectRows>0) {
                   result = true;
               }
            }//connection is available
        } finally {//close anyway
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean updateAccount(RegistrationDTO dto) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBhelper.getConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getPassword());
                stm.setBoolean(2, dto.isRole());
                stm.setString(3, dto.getUsername());
                int affectedRows = stm.executeUpdate();
                if (affectedRows>0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
        
    }
    
    public boolean createAccount(RegistrationDTO account) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;//khai bao, declare
        PreparedStatement stm = null;
        

        try {
            //1. get connection object(check valid connection
            con = DBhelper.getConnection();
            if (con != null) {
                //2. write SQL string
                String sql = "Insert Into Registration("
                        + "username, password, lastName, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. Excute query
                int affectRows = stm.executeUpdate();
                //5. process result
               if (affectRows>0) {
                   result = true;
               }
            }//connection is available
        } finally {//close anyway
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}