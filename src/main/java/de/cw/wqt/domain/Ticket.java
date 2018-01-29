package de.cw.wqt.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Ticket {

    private Integer id;

    private String name;

    private Timestamp start;

    private Timestamp end;

    private Timestamp arrival;

    private TicketType type;
}
