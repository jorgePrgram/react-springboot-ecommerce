package com.example.tiendaElectronica.infraestructure.security.Jwt;

import com.example.tiendaElectronica.infraestructure.security.CustomerDetailService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    private String username;
    Claims claims;
    @Autowired
    CustomerDetailService customerDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String path=request.getServletPath();
      if(path.matches("/api/usuarios/login") ||path.startsWith("/api/productos")
              || path.matches("/api/usuarios")||path.startsWith("/api/categoria")){
          filterChain.doFilter(request,response);
          return;
      }

        String authorizationHeader=request.getHeader("Authorization");
        String token=null;
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
          token = authorizationHeader.substring(7);
          username = jwtUtil.extractUsername(token);
          claims = jwtUtil.extractClaims(token);
        }
      if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails userDetails=customerDetailService.loadUserByUsername(username);
          if(jwtUtil.validateToken(token, userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
      }
    filterChain.doFilter(request,response);
    }
}