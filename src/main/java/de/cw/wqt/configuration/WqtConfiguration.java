package de.cw.wqt.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiLocatorDelegate;

import de.cw.wqt.dao.WqtDao;
import de.cw.wqt.domain.ContactInformation;
import de.cw.wqt.service.WqtService;

@Configuration
@ComponentScan("de.cw")
public class WqtConfiguration {

    private static final JndiLocatorDelegate jndi = JndiLocatorDelegate.createDefaultResourceRefLocator();

    @Value("${contact.email}")
    private String email;

    @Value("${contact.phoneNumber}")
    private String phoneNumber;

    @Bean
    public WqtService wqtService() {
        return new WqtService(new WqtDao(lookupDataSource("jdbc/wqt")), new ContactInformation(email, phoneNumber));
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() throws NamingException {
        String propertyLocation = jndi.lookup("environment_properties", String.class);
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new FileSystemResource(propertyLocation));
        return propertyPlaceholderConfigurer;
    }

    private DataSource lookupDataSource(final String jndiName) {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(jndiName);
    }

}
