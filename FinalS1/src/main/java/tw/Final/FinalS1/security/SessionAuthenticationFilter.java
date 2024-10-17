package tw.Final.FinalS1.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class SessionAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String userUUID = (session != null) ? (String) session.getAttribute("userUUID") : null;

        if (userUUID != null) {
            // 使用session裡面userUUID判斷是否登陸,若有登錄設置認證
            Authentication authentication = new PreAuthenticatedAuthenticationToken(userUUID, null, 
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }else {
            // 如果未登錄，轉向到登錄頁
            // 但是要確保只在訪問受限制頁面時才轉向
            String requestURI = request.getRequestURI();
            if (requestURI.equals("/Account.html") || requestURI.equals("/checkout.html") || 
                requestURI.equals("/wishlist.html") || requestURI.equals("/cart.html")) {
                response.sendRedirect("/Signin.html");  // 重新轉到登錄頁
                return;
            } else {
                // 對於其他頁面不做轉向,直接繼續
                filterChain.doFilter(request, response);
            }
        }
    }
}
