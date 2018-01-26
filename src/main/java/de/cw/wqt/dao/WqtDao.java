package de.cw.wqt.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.User;
import de.cw.wqt.domain.mapper.TicketMapper;

public class WqtDao extends JdbcDaoSupport {

    public WqtDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    private Ticket getTicketFromList(List<Ticket> tickets) {
        if (tickets != null && tickets.size() == 1) {
            return tickets.get(0);
        } else {
            return null;
        }
    }

    public Ticket getPublicTicket() {
        List<Ticket> tickets = getJdbcTemplate().query("SELECT id, name, start, end FROM wqt.ticket WHERE end IS NULL ORDER BY id LIMIT 1",
                        new Object[] {}, new TicketMapper());
        return getTicketFromList(tickets);
    }

    public Ticket getPrivateTicket(User user) {
        List<Ticket> tickets = getJdbcTemplate().query("SELECT id, name, start, end FROM wqt.ticket WHERE name like ? ORDER BY id desc LIMIT 1",
                        new Object[] { user.getFullName() }, new TicketMapper());
        return getTicketFromList(tickets);
    }

    public void insertTicket(User user) {
        getJdbcTemplate().update("INSERT INTO ticket (name, start) VALUES (?, ?)", user.getFullName(), new Date());
    }

}
