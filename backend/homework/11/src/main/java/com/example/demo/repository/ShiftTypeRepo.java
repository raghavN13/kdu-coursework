package com.example.demo.repository;

import com.example.demo.dto.ShiftTypeDTO;
import com.example.demo.entities.ShiftType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ShiftTypeRepo {

    private final JdbcTemplate jdbcTemplate;

    public ShiftTypeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addShiftType(ShiftTypeDTO shiftTypeDTO) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, updated_at, time_zone, tenant_id) " +
                "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";
        jdbcTemplate.update(
                sql,
                UUID.randomUUID(),
                shiftTypeDTO.getShiftName(),
                shiftTypeDTO.getDescription(),
                shiftTypeDTO.isActive(),
                shiftTypeDTO.getTimezone(),
                shiftTypeDTO.getTenantId()
        );
    }

    public ShiftType getShiftTypeById(UUID shiftTypeId) {
        String sql = "SELECT * FROM shift_types WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shiftTypeId}, this::mapRowToShiftType);
    }

    public List<ShiftType> getAllShiftTypes() {
        String sql = "SELECT * FROM shift_types";
        return jdbcTemplate.query(sql, this::mapRowToShiftType);
    }

    private ShiftType mapRowToShiftType(ResultSet rs, int rowNum) throws SQLException {
        ShiftType shiftType = new ShiftType();
        shiftType.setShiftTypeId(UUID.fromString(rs.getString("id")));
        shiftType.setShiftName(rs.getString("uq_name"));
        shiftType.setDescription(rs.getString("description"));
        shiftType.setActive(rs.getBoolean("active"));
        shiftType.setCreatedAt(rs.getTime("created_at"));
        shiftType.setUpdatedAt(rs.getTime("updated_at"));
        shiftType.setTimeZone(rs.getString("time_zone"));
        shiftType.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        return shiftType;
    }
}
