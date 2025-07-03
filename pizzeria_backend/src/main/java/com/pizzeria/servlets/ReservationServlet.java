package com.pizzeria.servlets;

import com.pizzeria.service.ResService;
import com.pizzeria.model.Reservation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.IWebExchange;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/reservation")
public class ReservationServlet extends HttpServlet {

    @Autowired
    private ResService resService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private JakartaServletWebApplication webApp;

    @Override
    public void init() throws ServletException {
        // abilita @Autowired
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        // inizializza la “web application” Thymeleaf
        webApp = JakartaServletWebApplication.buildApplication(getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        List<Reservation> reservations = resService.getallRes();
        IWebExchange exchange = webApp.buildExchange(req, resp);

        WebContext ctx = new WebContext(exchange, req.getLocale());
        ctx.setVariable("reservations", reservations);

        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("admin/reservation", ctx, resp.getWriter());
    }
}
