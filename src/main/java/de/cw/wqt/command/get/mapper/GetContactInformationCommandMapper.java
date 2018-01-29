package de.cw.wqt.command.get.mapper;

import de.cw.wqt.command.get.GetContactInformationCommand;
import de.cw.wqt.domain.ContactInformation;

public class GetContactInformationCommandMapper {

    private GetContactInformationCommandMapper() {}

    public static GetContactInformationCommand map(ContactInformation contactInformation) {
        GetContactInformationCommand getContactInformationCommand = new GetContactInformationCommand();
        getContactInformationCommand.setEmail(contactInformation.getEmail());
        getContactInformationCommand.setPhoneNumber(contactInformation.getPhoneNumber());
        return getContactInformationCommand;
    }
}
