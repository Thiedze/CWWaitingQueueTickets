package de.cw.wqt.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@ImportResource({ "classpath:/ldap-security.xml" })
public class WqtSecurityConfiguration {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Bean
    protected AuthenticationProvider ldapAuthenticationProvider() throws Exception {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(ldapUrl);
        contextSource.afterPropertiesSet();
        DefaultLdapAuthoritiesPopulator defaultLdapAuthoritiesPopulator = new DefaultLdapAuthoritiesPopulator(contextSource, "ou=cim,ou=groups");
        defaultLdapAuthoritiesPopulator.setGroupRoleAttribute("cn");
        defaultLdapAuthoritiesPopulator.setSearchSubtree(true);
        defaultLdapAuthoritiesPopulator.setGroupSearchFilter("(uniqueMember={0})");

        GrantedAuthoritiesMapper grantedAuthoritiesMapper = new GrantedAuthoritiesMapper() {
            @Override
            public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
                Collection<? extends GrantedAuthority> mappedAuthorities = authorities.stream().map(authority -> {
                    String role = authority.getAuthority();
                    log.info(role);
                    return new SimpleGrantedAuthority(StringUtils.replaceEach(role, new String[] { "CIM-", "-USERS" }, new String[] { "", "" }));
                }).collect(Collectors.toList());
                return mappedAuthorities;
            }
        };

        LdapAuthoritiesPopulator ldapAuthoritiesPopulator = new LdapAuthoritiesPopulator() {
            @Override
            public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userDate, String userName) {
                Collection<GrantedAuthority> grantedAuthorities = defaultLdapAuthoritiesPopulator.getGrantedAuthorities(userDate, userName);
                return grantedAuthoritiesMapper.mapAuthorities(grantedAuthorities);
            }
        };

        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
        bindAuthenticator.setUserSearch(new FilterBasedLdapUserSearch("", "Uid={0}", contextSource));

        LdapAuthenticationProvider authenticationProvider = new LdapAuthenticationProvider(bindAuthenticator, ldapAuthoritiesPopulator);

        return authenticationProvider;
    }
}
