package tw.Final.FinalS1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
public class googleSecurity {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    .csrf(csrf -> csrf
                .ignoringRequestMatchers("/**") // 忽略 API 路徑下的所有請求的 CSRF 保護  使用postman tapi在解開
            )

	    .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/Account.html", "/checkout.html", "/wishlist.html", "/cart.html").authenticated() // 需要登陸才能訪問的頁面
	            .anyRequest().permitAll() // 其他請求不需要登陸
	        )
	        .addFilterBefore(new SessionAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
     
	        .oauth2Login(oauth2 -> oauth2
	            .loginPage("/final/googlelogin")
	            .defaultSuccessUrl("/final/googlelogin/success", true) // 登陸成功後重定向到主頁
	            .failureUrl("/Signin.html") // 登錄失敗後的重定向
	                );

	    return http.build(); // 建構 SecurityFilterChain
	}
	
	    @Bean
	    public ClientRegistrationRepository clientRegistrationRepository() {
	        return new InMemoryClientRegistrationRepository(googleClientRegistration());
	    }

	    private ClientRegistration googleClientRegistration() {
	        return ClientRegistration.withRegistrationId("google")
	            .clientId("ID")
	            .clientSecret("金鑰匙")
	            .scope("profile", "email")
	            .authorizationUri("https://accounts.google.com/o/oauth2/auth?prompt=consent")
	            .tokenUri("https://oauth2.googleapis.com/token")
	            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
	            .userNameAttributeName("sub") // 使用 Google ID
	            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
	            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	            .build();
	    }
}
