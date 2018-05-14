package com.template.micro.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest rq, HttpServletResponse re, Authentication e) throws IOException, ServletException {

        if(e == null){
            re.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else{

            re.setStatus(HttpServletResponse.SC_OK);
            PrintWriter printerWriter = re.getWriter();
            printerWriter.write("logout success");
            printerWriter.flush();

        }
    }

}
