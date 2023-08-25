package com.todaysfail.config.security;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.helper.SpringEnvironmentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final FilterConfig filterConfig;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Value("${swagger.user}")
    private String swaggerUser;

    @Value("${swagger.password}")
    private String swaggerPassword;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user =
                User.withUsername(swaggerUser)
                        .password(passwordEncoder().encode(swaggerPassword))
                        .roles("SWAGGER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable().cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().expressionHandler(expressionHandler());

        if (springEnvironmentHelper.isProdAndDevProfile()) {
            http.authorizeRequests().mvcMatchers(SwaggerPatterns).authenticated().and().httpBasic();
        }
        http.authorizeRequests()
                .mvcMatchers(SwaggerPatterns)
                .permitAll()
                .antMatchers("/api/v1/examples/health")
                .permitAll()
                .mvcMatchers("/api/v1/auth/oauth/**")
                .permitAll()
                .mvcMatchers("/api/v1/auth/token/refresh")
                .permitAll()
                .antMatchers("/api/v1/users/nickname/generate")
                .permitAll()
                .anyRequest()
                .hasRole("USER");
        http.apply(filterConfig);
        return http.build();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler =
                new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
}
