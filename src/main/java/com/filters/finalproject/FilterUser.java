/**
 * The filter check the entity "user" and allow access to user's servlets
 *
 * @author Kuznietsov Rostyslav
 */
package com.filters.finalproject;

import com.logic.finalproject.ServletChangeStatusToCompleted;
import com.logic.finalproject.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(filterName = "FilterUser", servletNames = {"ServletUser", "CreateNewOrder", "ServletCancelOrder", "ServletPayOrder", "LeaveFeedback"})
public class FilterUser implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(FilterUser.class);
    public void init(FilterConfig config) throws ServletException {
        logger.info("filter FilterUser init");
    }

    public void destroy() {
        logger.info("filer FilterUser destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("filter FilterUser doFilter started");
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        User user = (User) session.getAttribute("entityUser");
        if(user != null) {
            logger.info("filter FilterUser doFilter user = {}", user);
            chain.doFilter(request, response);
        } else {
            logger.warn("user = null in filter FilterUser. Send redirect to /autorisation-users");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/autorisation-users");
                requestDispatcher.forward(request, response);
               }
    }
}
