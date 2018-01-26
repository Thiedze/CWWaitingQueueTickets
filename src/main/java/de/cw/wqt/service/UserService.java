package de.cw.wqt.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import de.cw.wqt.domain.User;

public class UserService {

    private static final String SECURITY_CONTEXT_ATTRIBUTE = "SPRING_SECURITY_CONTEXT";

    private UserService() {}

    public static User readUserFromSession(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute(SECURITY_CONTEXT_ATTRIBUTE);
        LdapUserDetails userDetails = (LdapUserDetails) context.getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        String dn = userDetails.getDn();
        int index1 = dn.indexOf("cn=");
        int index2 = dn.indexOf(',', index1);
        String fullName = dn.substring(index1 + 3, index2);
        User user = new User(userName, fullName);

        userDetails.getAuthorities().forEach(authority -> user.addRole(authority.getAuthority()));

        return user;
    }

}
