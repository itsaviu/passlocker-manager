package com.ua.passlocker.manager.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ua.passlocker.manager.exceptions.UserNotExistException;
import com.ua.passlocker.manager.models.ContextHolder;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.security.SecurityContextHolder;
import com.ua.passlocker.manager.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ua.passlocker.manager.utils.Constants.HEADER_STRING;
import static com.ua.passlocker.manager.utils.Constants.TOKEN_PREFIX;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;


@Component
@Order(HIGHEST_PRECEDENCE)
@Slf4j
public class JWTAuthorizationFilter implements Filter {

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private UserDetailService userDetailService;

    private static final List<String> AUTH_WHITELIST =
            Arrays.asList(".*/v2/api-docs", ".*/swagger-resources", ".*/swagger-resources/.*", ".*/configuration/ui",
                    ".*/configuration/security",
                    ".*/swagger-ui.html",
                    ".*/webjars/.*", ".*/internal/.*");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResp = (HttpServletResponse) response;

        String token = httpServletRequest.getHeader(HEADER_STRING);

        String method = ((HttpServletRequest) request).getMethod();

        String requestURI = httpServletRequest.getRequestURI();

        boolean ignoreAuth = method.equals("OPTIONS") ||
                AUTH_WHITELIST.stream().anyMatch(pattern -> Pattern.matches(pattern, requestURI));

        if (ignoreAuth)
            chain.doFilter(request, response);
        else {
            if (!StringUtils.isEmpty(token)) {
                try {
                    JWTVerifier verifier = JWT.require(HMAC512(SECRET.getBytes()))
                            .build();
                    DecodedJWT jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
                    Claim sub = jwt.getClaim("emailId");
                    UserDetails userDetails = userDetailService.loadUserByEmailId(sub.asString()).orElseThrow(() -> new UserNotExistException("User not exist"));
                    SecurityContextHolder.setContextHolder(new ContextHolder(userDetails));
                    chain.doFilter(request, response);
                } catch (JWTVerificationException | UserNotExistException exception) {
                    log.error("Exception authentication", exception);
                    httpServletResp.sendError(HttpStatus.UNAUTHORIZED.value(), "User Un-Authorized");
                } catch (Exception ex) {
                    log.error("Exception in security check", ex);
                    httpServletResp.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong!");
                }
            } else
                httpServletResp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User Token is expired or invalid");
        }

    }

    @Override
    public void destroy() {
        SecurityContextHolder.removeContextHolder();
    }
}
