package de.cw.wqt.service;

import de.cw.wqt.dao.WqtDao;
import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.TicketType;
import de.cw.wqt.domain.User;

public class WqtService {

    private WqtDao wqtDao;

    public WqtService(WqtDao wqtDao) {
        this.wqtDao = wqtDao;
    }

    public Ticket getTicket(TicketType ticketType, User user) {
        if (TicketType.PUBLIC.equals(ticketType)) {
            return wqtDao.getPublicTicket();
        } else if (TicketType.PRIVATE.equals(ticketType)) {
            return wqtDao.getPrivateTicket(user);
        } else {
            return new Ticket();
        }
    }

    public Ticket createTicket(User user) {
        wqtDao.insertTicket(user);
        return wqtDao.getPrivateTicket(user);
    }

}
