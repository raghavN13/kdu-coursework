package com.example.demo.repository;
import com.example.demo.dto.ShiftsDTO;
import com.example.demo.entities.Shifts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ShiftsRepo {

    private final JdbcTemplate jdbcTemplate;

    public ShiftsRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addShift(ShiftsDTO shiftsDTO) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, created_at, updated_at, time_zone, tenant_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";
        jdbcTemplate.update(
                sql,
                UUID.randomUUID(),  // generate a random UUID for id
                shiftsDTO.getShiftTypeId(),
                shiftsDTO.getName(),
                shiftsDTO.getDateStart(),
                shiftsDTO.getDateEnd(),
                shiftsDTO.getTimeStart(),
                shiftsDTO.getTimeEnd(),
                shiftsDTO.getTimeZone(),
                shiftsDTO.getTenantId()
        );
    }

    public Shifts getShiftById(UUID shiftId) {
        String sql = "SELECT * FROM shifts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shiftId}, this::mapRowToShift);
    }

    public List<Shifts> getAllShifts() {
        String sql = "SELECT * FROM shifts";
        return jdbcTemplate.query(sql, this::mapRowToShift);
    }

    private Shifts mapRowToShift(ResultSet rs, int rowNum) throws SQLException {
        Shifts shifts = new Shifts();
        shifts.setShiftId(UUID.fromString(rs.getString("id")));
        shifts.setShiftTypeId(UUID.fromString(rs.getString("shift_type_id")));
        shifts.setName(rs.getString("name"));
        shifts.setDateStart(rs.getDate("date_start"));
        shifts.setDateEnd(rs.getDate("date_end"));
        shifts.setTimeStart(rs.getTime("time_start"));
        shifts.setTimeEnd(rs.getTime("time_end"));
        shifts.setCreatedAt(rs.getTimestamp("created_at"));
        shifts.setUpdatedAt(rs.getTimestamp("updated_at"));
        shifts.setTimeZone(rs.getString("time_zone"));
        shifts.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        return shifts;
    }
}
