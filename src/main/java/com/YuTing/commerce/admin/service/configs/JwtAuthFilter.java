package com.YuTing.commerce.admin.service.configs;

import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import com.YuTing.commerce.admin.service.services.JwtService;
import com.YuTing.commerce.admin.service.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;


    @Autowired
    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //從http headers中獲取Authorization欄位->bearer
        String authHeader = request.getHeader("Authorization");
        // 1. 檢查Authorization格式是否正確
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //該次請求過濾器結束生命週期=>將請求繼續往下傳遞....
            filterChain.doFilter(request, response);
            return;
        }
        //若開頭格式(Bearer...)正確,則擷取第七字元開始的字串(實際jwt)
        String jwtToken = authHeader.substring(7);
        String email = jwtService.getEmailFromToken(jwtToken);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // db裡面找到對應的userEmail
            Optional<User> user = userService.getUserByEmail(email);
            //todo 驗證token是否過期或無效
            if (user.isPresent()) {

                //*** 若使用Spring Security (library)必須包含 授權 (Authorization)邏輯->「該用戶能做什麼？」***
                List<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.get().getRole()));
                //該token並非 jwt token(亂碼),而是Spring Security內部使用的token(包含user & authorities)
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null,authorities);
                //將內部使用的token 投進 Spring Security 認證箱 SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //該是請求過濾器結束生命週期=>將請求繼續往下傳遞..
        filterChain.doFilter(request, response);
    }
}
