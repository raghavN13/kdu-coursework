
package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepo {

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(UserDTO userDTO) {
        String sql = "INSERT INTO users (id, username, logged_in, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                UUID.randomUUID(),
                userDTO.getName(),
                userDTO.getLoggedIn(),
                userDTO.getTimeZone(),
                userDTO.getTenantId()
        );
    }

    public User getUserById(UUID userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, this::mapRowToUser);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(UUID.fromString(rs.getString("id")));
        user.setUsername(rs.getString("username"));
        user.setLoggedin(rs.getShort("logged_in"));
        user.setTime_zone(rs.getString("time_zone"));
        user.setTenant_id(UUID.fromString(rs.getString("tenant_id")));
        return user;
    }
}
