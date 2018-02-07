package de.cw.wqt.command.get.mapper;

import java.util.ArrayList;
import java.util.List;

import de.cw.wqt.command.get.GetTicketCommand;
import de.cw.wqt.domain.Ticket;
import de.cw.wqt.domain.TicketType;

public class GetTicketCommandMapper {

    private GetTicketCommandMapper() {
    }

    public static Ticket map(GetTicketCommand getTicketCommand) {
        Ticket ticket = new Ticket();
        ticket.setType(TicketType.convert(getTicketCommand.getType()));
        return ticket;
    }

    public static List<GetTicketCommand> map(List<Ticket> tickets) {
        List<GetTicketCommand> getTicketCommands = new ArrayList<>();
        if (tickets != null && !tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                getTicketCommands.add(map(ticket));
            }
        }
        return getTicketCommands;
    }

    public static GetTicketCommand map(Ticket ticket) {
        GetTicketCommand getTicketCommand = new GetTicketCommand();
        if (ticket != null) {
            getTicketCommand.setId(ticket.getId());
            getTicketCommand.setName(ticket.getName());
            getTicketCommand.setStart(ticket.getStart() != null ? ticket.getStart().toString() : null);
            getTicketCommand.setEnd(ticket.getEnd() != null ? ticket.getEnd().toString() : null);
            getTicketCommand.setType(ticket.getType() != null ? ticket.getType().toString() : null);
            getTicketCommand.setArrival(ticket.getArrival() != null ? ticket.getArrival().toString() : null);
        }
        return getTicketCommand;
    }
}
