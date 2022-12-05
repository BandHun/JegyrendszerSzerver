package hu.bandi.szerver.services.jwt;

import hu.bandi.szerver.services.interfaces.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
                                    final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws
            ServletException, IOException {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println(httpServletRequest.getRequestURL());

        final String authorizationHeader = httpServletRequest.getHeader("authorization");
        System.out.println("Header JWTFILTER");
        System.out.println(authorizationHeader);
        /*List<String> lines = httpServletRequest.getReader().lines().collect(Collectors.toList());
        for(String l : lines){
            System.out.println(l);
        }
        /**/

        String token = null;
        String userName = null;

        Enumeration<String> hs = httpServletRequest.getHeaderNames();
        String a = hs.nextElement();

        /*while(a!=null){
            System.out.println(a);
            a=hs.nextElement();
        }*/

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            System.out.println("TOKEN");
            System.out.println(token);
            if(token.length()>4){
                try{
                    System.out.println("BENT");
                    userName = jwtUtil.extractUsername(token);}catch (ExpiredJwtException e){
                    httpServletResponse.setStatus(401);
                    return;
                }
            }
            else {
                token=null;
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println(userName);

            final UserDetails userDetails = userService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Max-Age", "180");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
