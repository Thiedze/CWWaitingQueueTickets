package de.cw.wqt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.cw.wqt.command.get.GetTicketCommand;
import de.cw.wqt.command.get.mapper.GetTicketCommandMapper;
import de.cw.wqt.domain.TicketType;
import de.cw.wqt.service.UserService;
import de.cw.wqt.service.WqtService;

@Controller
@RequestMapping(value = "wqt")
public class WqtController {

    @Autowired
    private WqtService wqtService;

    @RequestMapping(value = "ticket", method = RequestMethod.GET)
    @ResponseBody
    public GetTicketCommand getTicket(@RequestParam String type, HttpServletRequest request) {
        return GetTicketCommandMapper.map(wqtService.getTicket(TicketType.convert(type), UserService.readUserFromSession(request.getSession())));
    }

    @RequestMapping(value = "ticket", method = RequestMethod.POST)
    @ResponseBody
    public GetTicketCommand createTicket(HttpServletRequest request) {
        return GetTicketCommandMapper.map(wqtService.createTicket(UserService.readUserFromSession(request.getSession())));
    }

}
