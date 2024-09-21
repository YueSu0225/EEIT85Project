package tw.Final.FinalS1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class googleSecurity {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/**").permitAll() // 允許訪問登陸和 OAuth2 相關的 URL
	            .anyRequest().authenticated() // 其餘需要驗證
	        )
	        .oauth2Login(oauth2 -> oauth2
	            .loginPage("/final/googlelogin")
	            .defaultSuccessUrl("/final/googlelogin/success", true) // 成功后重定向到主頁
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
	            .clientId("GOOGLE_CLIENT_ID")
	            .clientSecret("GOOGLE_CLIENT_SECRET")
	            .scope("profile", "email")
	            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
	            .tokenUri("https://oauth2.googleapis.com/token")
	            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
	            .userNameAttributeName("sub") // 使用 Google ID
	            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
	            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	            .build();
	    }
}
