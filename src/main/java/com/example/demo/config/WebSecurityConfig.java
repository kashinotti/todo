package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//ログイン処理を行うインスタンスをDI
	private final UserDetailsService userDetailsService;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/login", "/user/signup", "/user/create")
			.permitAll()
			.anyRequest().authenticated();
		
		http.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/login") //ログイン画面の設定
		.permitAll()
		.failureUrl("/login") //ログイン失敗時に遷移する画面の設定
		.usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/", true);
		
		http.logout()
			.logoutUrl("/logout") //ログアウト処理を行うページ指定。ここにPOSTするとログアウトする
			.logoutSuccessUrl("/login"); //ログアウト成功時の遷移先を指定
		
		http.csrf().disable(); //CSRFオプションの無効
	}
	
	 /**
     * パスワードのハッシュ化を行うアルゴリズムを返す
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * ログイン処理の設定
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // ログイン処理時のユーザー情報をDBから取得する
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
