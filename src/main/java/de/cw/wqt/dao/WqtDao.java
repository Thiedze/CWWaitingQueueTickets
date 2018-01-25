package de.cw.wqt.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.mapper.TicketMapper;

public class WqtDao extends JdbcDaoSupport {

    public WqtDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    public Ticket getCurrentTicket() {
        List<Ticket> tickets = getJdbcTemplate().query("SELECT id, name, start, end FROM wqt.ticket ORDER BY id desc LIMIT 1", new Object[] {},
                        new TicketMapper());

        if (tickets != null && tickets.size() == 1) {
            return tickets.get(0);
        } else {
            return null;
        }
    }

}
