package de.cw.wqt.domain;

public enum TicketType {

    UNKOWN, PRIVATE, PUBLIC;

    public static TicketType convert(String type) {
        for (TicketType ticketType : TicketType.values()) {
            if (ticketType.toString().equalsIgnoreCase(type)) {
                return ticketType;
            }
        }

        return UNKOWN;
    }

}
