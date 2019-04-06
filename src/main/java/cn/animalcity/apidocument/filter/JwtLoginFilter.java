package cn.animalcity.apidocument.filter;

import cn.animalcity.apidocument.dto.ResultConstants;
import cn.animalcity.apidocument.utils.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            String loginName = request.getParameter("loginName");
            String password = request.getParameter("password");

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginName, password, new ArrayList<>()
                    ));
            return authenticate;
        } catch (Exception e) {
            throw new UsernameNotFoundException(request.getParameter("loginName"));
        }
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {


        String username = ((User) authResult.getPrincipal()).getUsername();

        String token = TokenUtil.createToken(username, false);

        response.addHeader(TokenUtil.TOKEN_HEADER, TokenUtil.TOKEN_PREFIX + token);
        response.getWriter().println("{\"code\":"+ResultConstants.SUCCESS.getCode()+", \"msg\":\""+ ResultConstants.SUCCESS.getMsg()+"\", \"data\":\"" + TokenUtil.TOKEN_PREFIX + token + "\"}");
    }

    /**
     * 验证失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.getWriter().println(
                "{\"code\":" + ResultConstants.BAD_CREDENTIAL.getCode()
                + ", \"msg\":\"" + ResultConstants.BAD_CREDENTIAL.getMsg()
                + "\", \"data\":null}");
    }
}
