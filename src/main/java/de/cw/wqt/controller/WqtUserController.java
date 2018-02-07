package de.cw.wqt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.cw.wqt.domain.User;

@Controller
@RequestMapping(value = "user")
public class WqtUserController {

    private static final String SECURITY_CONTEXT_ATTRIBUTE = "SPRING_SECURITY_CONTEXT";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User getUser(HttpServletRequest request) {
        return readUserFromSession(request.getSession());
    }

    private User readUserFromSession(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute(SECURITY_CONTEXT_ATTRIBUTE);
        LdapUserDetails userDetails = (LdapUserDetails) context.getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        String dn = userDetails.getDn();
        int index1 = dn.indexOf("cn=");
        int index2 = dn.indexOf(",", index1);
        String fullName = dn.substring(index1 + 3, index2);
        User user = new User(userName, fullName);

        userDetails.getAuthorities().forEach(authority -> user.addRole(authority.getAuthority()));

        return user;
    }

}