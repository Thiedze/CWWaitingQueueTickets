package de.cw.wqt.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.User;
import de.cw.wqt.domain.mapper.TicketMapper;

public class WqtDao extends JdbcDaoSupport {

    private static final String TICKET_FIELDS = "id, name, start, end, arrival";

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
        List<Ticket> tickets = getJdbcTemplate().query("SELECT " + TICKET_FIELDS + " FROM ticket WHERE arrival IS NOT NULL ORDER BY id desc LIMIT 1",
                        new Object[] {}, new TicketMapper());
        return getTicketFromList(tickets);
    }

    public Ticket getPrivateTicket(User user) {
        List<Ticket> tickets =
                        getJdbcTemplate().query("SELECT " + TICKET_FIELDS + " FROM ticket WHERE name like ? AND end IS NULL ORDER BY id desc LIMIT 1",
                                        new Object[] { user.getFullName() }, new TicketMapper());
        return getTicketFromList(tickets);
    }

    public List<Ticket> getTickets() {
        return getJdbcTemplate().query("SELECT " + TICKET_FIELDS + " FROM ticket WHERE end IS NULL ORDER BY id", new Object[] {}, new TicketMapper());
    }

    public void insertTicket(User user) {
        getJdbcTemplate().update("INSERT INTO ticket (name, start) VALUES (?, ?)", user.getFullName(), new Date());
    }

    public void startNextArrival() {
        getJdbcTemplate().update("UPDATE ticket SET arrival=? WHERE end is NULL ORDER BY id LIMIT 1", new Date());
    }

    public void closeAllTickets(User user) {
        getJdbcTemplate().update("UPDATE ticket SET end=?, arrival=? WHERE name like ?", new Date(), new Date(), user.getFullName());
    }

    public void closeOldestTicket() {
        getJdbcTemplate().update("UPDATE ticket SET end=? WHERE end is NULL ORDER BY id LIMIT 1", new Date());
    }

    public void closeTicketsNotArriveled() {
        getJdbcTemplate().update("UPDATE ticket SET end=? WHERE end IS NULL AND arrival < DATE_SUB(NOW(),INTERVAL 10 MINUTE)", new Date());
    }

}
