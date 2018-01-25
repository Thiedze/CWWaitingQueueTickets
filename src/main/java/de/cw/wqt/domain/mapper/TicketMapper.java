package de.cw.wqt.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.cw.wqt.domain.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int index) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setName(rs.getString("name"));
        ticket.setStart(rs.getDate("start"));
        ticket.setEnd(rs.getDate("end"));
        return ticket;
    }

}
