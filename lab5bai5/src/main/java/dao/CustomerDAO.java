package dao;

import util.DBUtil;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;
public class CustomerDAO {

    // check trùng mã KH
    public boolean existsCustomerId(String id) throws Exception {
        String sql = "SELECT 1 FROM dbo.Customer WHERE CustomerId = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeQuery().next();
        }
    }

    // check trùng email
    public boolean existsEmail(String email) throws Exception {
        String sql = "SELECT 1 FROM dbo.Customer WHERE Email = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeQuery().next();
        }
    }

    // insert dữ liệu
    public void insert(model.Customer c) throws Exception {
        String sql = """
            INSERT INTO dbo.Customer
            (CustomerId, FullName, Email, Phone, Address, Password, BirthDate, Gender)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCustomerId());
            ps.setString(2, c.getFullName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getPassword());
            ps.setDate(7,
                    c.getBirthDate() == null ? null :
                            java.sql.Date.valueOf(c.getBirthDate()));
            ps.setString(8, c.getGender());

            ps.executeUpdate();
        }
    }
    // xóa theo CustomerId (dùng cho test)
    public void deleteByCustomerId(String id) throws Exception {
        String sql = "DELETE FROM dbo.Customer WHERE CustomerId = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    // xóa theo Email (dùng cho test)
    public void deleteByEmail(String email) throws Exception {
        String sql = "DELETE FROM dbo.Customer WHERE Email = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }

}