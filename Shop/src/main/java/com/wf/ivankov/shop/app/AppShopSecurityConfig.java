package com.wf.ivankov.shop.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.wf.ivankov.shop.security.InternalDbAuthoritiesPopulator;

/**
 * @author Ivankov_A
 *
 */
@Configuration
@EnableWebSecurity
public class AppShopSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Autowired
	private AppShopProperties props;
	
	@Autowired
	private InternalDbAuthoritiesPopulator internalDbAuthoritiesPopulator;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	@DependsOn("AppShopProperties")
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		if (props.isProduction()) {
			LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAp = auth.ldapAuthentication();
			ldapAp.userDnPatterns("uid={0},ou=people");
			ldapAp.groupSearchBase("ou=groups");
			ldapAp.contextSource(contextSource());
			ldapAp.passwordCompare().passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute("userPassword");
			ldapAp.ldapAuthoritiesPopulator(internalDbAuthoritiesPopulator);
		} else {
			auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
			auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
			auth.inMemoryAuthentication().withUser("optovik").password("optovik").roles("OPT");
		}
	}

	@Bean
	@Lazy
	public DefaultSpringSecurityContextSource contextSource() {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
	}
}