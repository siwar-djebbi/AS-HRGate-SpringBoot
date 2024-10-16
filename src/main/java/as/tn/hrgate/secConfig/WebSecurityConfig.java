package as.tn.hrgate.secConfig;

////import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import as.tn.hrgate.secConfig.Jwt.AuthEntryPointJwt;
import as.tn.hrgate.secConfig.Jwt.AuthTokenFilter;
import as.tn.hrgate.Service.UserDetailsServiceImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  { // extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//@Bean
//public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//}
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
}
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/signin").permitAll()
                .antMatchers("/api/auth/signup").permitAll()
                .antMatchers("/User/create").permitAll()
                .antMatchers("/User/alll").permitAll()
                .antMatchers("/User/getCurrent").permitAll()
                .antMatchers("/User/editPassword").permitAll()
                .antMatchers("/User/delete").permitAll()
                .antMatchers("/User/editCurrent").permitAll()
                .antMatchers("/User/ban").permitAll()
                .antMatchers("/User/stats/banned-users").permitAll()
                .antMatchers("/Conge/allDemandesConges").permitAll()
                .antMatchers("/Conge/addCongeRequesttt").permitAll()
                .antMatchers("/Conge/addCongeRequest").permitAll()
                .antMatchers("/Conge/delete/{{idLibreDemConge}}").permitAll()
                .antMatchers("/Autorisation/addAutoRequest").permitAll()
                .antMatchers("/Autorisation/allDemandesAuto").permitAll()
                .antMatchers("/Conge/congeduration/{startDate}/{endDate}").permitAll()
                .antMatchers("/Autorisation/add").permitAll()
                .antMatchers("/Conge/accept/{{idLibreDemConge}}").permitAll()
                .antMatchers("/Conge/{{idLibreDemConge}}/refuse").permitAll()
                .antMatchers("/Conge/user/{{matPers}}").permitAll()
                .antMatchers("/Situation/addSituationRequest").permitAll()
                .antMatchers("/Situation/allDemandesSituations").permitAll()
                .antMatchers("/Situation/emailsituation").permitAll()
                .antMatchers("/notification/**").permitAll()
                .antMatchers("/ws-notification/**").permitAll()
                .antMatchers("/Situation/downloadFile/{{fileName}}").permitAll()
                .antMatchers("/Situation/downloadFile/**").permitAll()
                .antMatchers("/getNotificationsByMatPers/{matPers}").permitAll()
                .antMatchers("/Autorisation/assignAutoToUser/{idLibreDemAuto}/{matPers}").permitAll()
                .antMatchers("/Autorisation/delete/{idLibreDemAuto}").permitAll()
                .antMatchers("/User/editProfile").permitAll()
                .antMatchers("/Autorisation/gett/{{idLibreDemAuto}}").permitAll()
                .antMatchers("/Conge/assignLeaveToUser/{idLibreDemConge}/{matPers}").permitAll()
                .antMatchers("/Conge/getLeaveRequestsByMatPers/{matPers}").permitAll()
                .antMatchers("/Conge/get/{idLibreDemConge}").permitAll()










                .antMatchers("/EmailSender/findByAdress/{emailAdress}").permitAll()
                .antMatchers("/EmailTemplate/ListAll").permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs").permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }



}
