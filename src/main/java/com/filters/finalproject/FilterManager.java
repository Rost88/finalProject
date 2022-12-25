/**
 * The filter check the entity "manager" and allow access to manager's servlets
 *
 * @author Kuznietsov Rostyslav
 */
package com.filters.finalproject;

import com.logic.finalproject.Manager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(filterName = "FilterManager", servletNames ={"ServletManager", "TopOnUsersBalance", "ServletSetCraftsman", "ServletChangeOrdersPrice"})
public class FilterManager implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(FilterManager.class);
    public void init(FilterConfig config) throws ServletException {
        logger.info("filter FilterManager init");
    }

    public void destroy() {
        logger.info("filer FilterManager destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("filter FilterManager doFilter started");
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        Manager manager = (Manager) session.getAttribute("entityManager");
        if(manager != null) {
            logger.info("filter FilterManager doFilter manager = {}", manager);
            chain.doFilter(request, response);
        } else {
            logger.warn("manager = null in filter FilterManager. Send redirect to /autorisation-managers");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/autorisation-managers");
            requestDispatcher.forward(request, response);
        }
    }
}
