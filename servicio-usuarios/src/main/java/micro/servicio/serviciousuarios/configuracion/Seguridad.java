package micro.servicio.serviciousuarios.configuracion;

import micro.servicio.serviciousuarios.usuario.UsuarioDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class Seguridad  extends WebSecurityConfigurerAdapter {
    @Autowired
    UsuarioDetailsServices usuarioDetailsServices;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetailsServices).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/dbconsole").permitAll()
                .antMatchers("/").hasRole("ADMIN")
                .and().formLogin();
    }
}
