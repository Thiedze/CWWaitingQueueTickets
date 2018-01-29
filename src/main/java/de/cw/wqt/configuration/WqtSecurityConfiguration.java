package de.cw.wqt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WqtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/rest/**").fullyAuthenticated();
        http.formLogin().loginPage("/login.html");
        http.formLogin().defaultSuccessUrl("/app");
        http.formLogin().successForwardUrl("/index.html");
        http.formLogin().failureUrl("/login.html?error=true");
        http.logout().logoutSuccessUrl("/login.html");
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * auth.ldapAuthentication().groupSearchBase("ou=cim,ou=groups");
         * auth.ldapAuthentication().groupSearchFilter("(uniqueMember={0})");
         * auth.ldapAuthentication().groupRoleAttribute("cn");
         * auth.ldapAuthentication().userSearchFilter("Uid={0}");
         * auth.ldapAuthentication().contextSource().url(ldapUrl).and().passwordCompare().
         * passwordEncoder(new LdapShaPasswordEncoder()) .passwordAttribute("userPassword");
         */
    }

}
