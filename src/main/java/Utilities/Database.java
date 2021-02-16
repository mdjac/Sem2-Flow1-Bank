package Utilities;

import Entities.Customer;
import Entities.Employee;
import Entities.User;
import Exceptions.AuthenticationException;
import Logging.LogFile;

import java.sql.*;

public class Database implements AutoCloseable {
    private final String db_url;
    private final String db_user;
    private final String db_password;

    private Connection con;
    private PreparedStatement ps_create_user;
    private PreparedStatement ps_validate_credentials;
    private PreparedStatement ps_create_account;

    public Database(String db_url, String db_user, String db_password) throws SQLException
    {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_password = db_password;
        prepareConnection();
    }

    @Override
    public void close() throws Exception
    {
        con.close();
    }
    private void prepareConnection() throws SQLException
    {
        con = DriverManager.getConnection(db_url, db_user, db_password);
        ps_create_user = con.prepareStatement(   "INSERT INTO Users (username,password,name,address,userType_id) VALUES(?,?,?,?,?)");
        ps_validate_credentials = con.prepareStatement(   "SELECT * from Users where Username = ? and Password = ?");
        ps_create_account = con.prepareStatement(   "INSERT INTO account (username) values (?)");
    }

    public boolean create_user (String username, String password, int type, String name, String address) throws SQLException {
        try
        {
            ps_create_user.setString(1,username);
            ps_create_user.setString(2,password);
            ps_create_user.setString(3,name);
            ps_create_user.setString(4,address);
            ps_create_user.setInt(5,type);
            if (ps_create_user.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        //Only creates account if usertype == customer == 2
        if(type == 2){
            create_account(username);
        }
       return true;
    }

    public User validate_credentials (String username, String password) throws SQLException, AuthenticationException {
        User user;
        try {
            ps_validate_credentials.setString(1, username);
            ps_validate_credentials.setString(2, password);
            try(ResultSet rs = ps_validate_credentials.executeQuery())
            {
                while (rs.next()){
                    LogFile.writeToLog("Login from username: " +username);
                    String rs_username = rs.getString(1);
                    String rs_name = rs.getString(3);
                    String rs_address = rs.getString(4);
                    int rs_typeID = rs.getInt(5);
                    if(rs_typeID == 1){
                        user = new Employee(rs_name,rs_username,rs_address);
                    }
                    else
                    {
                        user = new Customer(rs_name,rs_username,rs_address);
                    }
                    return user;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        LogFile.writeToLog("Failed login from username: " +username);
        throw new AuthenticationException("Failed to login with username: " + username);
    }

    private boolean create_account(String username){
        try
        {
            ps_create_account.setString(1,username);
            if (ps_create_account.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
