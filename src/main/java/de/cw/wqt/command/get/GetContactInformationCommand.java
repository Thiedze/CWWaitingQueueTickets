package de.cw.wqt.command.get;

import lombok.Data;

@Data
public class GetContactInformationCommand {

    private String email;

    private String phoneNumber;

}
