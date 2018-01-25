package de.cw.wqt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.cw.wqt.domain.Ticket;
import de.cw.wqt.service.WqtService;

@Controller
@RequestMapping(value = "wqt")
public class WqtController {

    @Autowired
    private WqtService wqtService;

    @RequestMapping(value = "currentTicket", method = RequestMethod.GET)
    @ResponseBody
    public Ticket getCurrentTicket() {
        return wqtService.getCurrentTicket();
    }

}
