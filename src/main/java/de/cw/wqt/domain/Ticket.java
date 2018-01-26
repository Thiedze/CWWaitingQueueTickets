package de.cw.wqt.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Ticket {

    private Integer id;

    private String name;

    private Date start;

    private Date end;

    private TicketType type;
}
