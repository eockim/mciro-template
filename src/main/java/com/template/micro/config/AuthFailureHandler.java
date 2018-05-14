package com.template.micro.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest rq, HttpServletResponse re, AuthenticationException e) throws IOException, ServletException {

        re.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        log.info("failure handler working!");

        PrintWriter writer = re.getWriter();
        writer.write(e.getMessage());
        writer.flush();

    }
}
