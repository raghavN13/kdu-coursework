package com.example.demo.repository;

import com.example.demo.dto.ShiftUserDTO;
import com.example.demo.entities.ShiftUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ShiftUserRepo {

    private final JdbcTemplate jdbcTemplate;

    public ShiftUserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addShiftUser(ShiftUserDTO shiftUserDTO) {
        String sql = "INSERT INTO shift_user (id, shift_id, user_id, tenant_id) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                UUID.randomUUID(),
                shiftUserDTO.getShiftId(),
                shiftUserDTO.getUserId(),
                shiftUserDTO.getTenantId()
        );
    }

    public ShiftUser getShiftUserById(UUID shiftUserId) {
        String sql = "SELECT * FROM shift_user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shiftUserId}, this::mapRowToShiftUser);
    }

    public List<ShiftUser> getAllShiftUsers() {
        String sql = "SELECT * FROM shift_user";
        return jdbcTemplate.query(sql, this::mapRowToShiftUser);
    }

    private ShiftUser mapRowToShiftUser(ResultSet rs, int rowNum) throws SQLException {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.fromString(rs.getString("id")));
        shiftUser.setShiftId(UUID.fromString(rs.getString("shift_id")));
        shiftUser.setUserId(UUID.fromString(rs.getString("user_id")));
        shiftUser.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        // Map other fields as needed
        return shiftUser;
    }
}
