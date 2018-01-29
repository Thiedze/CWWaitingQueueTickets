package de.cw.wqt.service;

import java.util.List;

import de.cw.wqt.dao.WqtDao;
import de.cw.wqt.domain.ContactInformation;
import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.TicketType;
import de.cw.wqt.domain.User;

public class WqtService {

    private WqtDao wqtDao;

    private ContactInformation contactInformation;

    public WqtService(WqtDao wqtDao, ContactInformation contactInformation) {
        this.wqtDao = wqtDao;
        this.contactInformation = contactInformation;
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
        wqtDao.closeAllTickets(user);
        wqtDao.insertTicket(user);
        return wqtDao.getPrivateTicket(user);
    }

    public List<Ticket> getTickets() {
        return wqtDao.getTickets();
    }

    public List<Ticket> updateTicket() {
        wqtDao.closeOldestTicket();
        return wqtDao.getTickets();
    }

    public List<Ticket> startTicket() {
        wqtDao.startNextArrival();
        return wqtDao.getTickets();
    }

    public void checkTickets() {
        wqtDao.closeTicketsNotArriveled();
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

}
