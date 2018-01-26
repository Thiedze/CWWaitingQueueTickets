package de.cw.wqt.command.get;

import lombok.Data;

@Data
public class GetTicketCommand {

    private Integer id;

    private String name;

    private String start;

    private String end;
    
    private String type;

}
