package cn.animalcity.apidocument.filter;


import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.utils.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenUtil.TOKEN_HEADER);

        if (header == null || !header.startsWith(TokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            response.getWriter().println(
                    "{\"code\":" + ResultConstants.BAD_CREDENTIAL.getCode()
                    + ", \"msg\":\"" + ResultConstants.BAD_CREDENTIAL.getMsg()
                    + "\", \"data\":null}");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token = header.replace(TokenUtil.TOKEN_PREFIX, "");
        String principal = TokenUtil.getUsername(token);

        if (principal != null) {
            return new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
        }

        return null;
    }

}
