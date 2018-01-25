package de.cw.wqt.service;

import de.cw.wqt.dao.WqtDao;
import de.cw.wqt.domain.Ticket;

public class WqtService {

    private WqtDao wqtDao;

    public WqtService(WqtDao wqtDao) {
        this.wqtDao = wqtDao;
    }

    public Ticket getCurrentTicket() {
        return wqtDao.getCurrentTicket();
    }

}
