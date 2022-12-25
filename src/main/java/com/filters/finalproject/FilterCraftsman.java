/**
 * The filter check the entity "craftsman" and allow access to craftsman's servlets
 *
 * @author Kuznietsov Rostyslav
 */
package com.filters.finalproject;

import com.logic.finalproject.Craftsman;
import com.logic.finalproject.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(filterName = "FilterCraftsman", servletNames ={"ServletCraftsman", "ServletChangeStatusToInProgress", "ServletChangeStatusToCompleted"})
public class FilterCraftsman implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(FilterCraftsman.class);
    public void init(FilterConfig config) throws ServletException {
        logger.info("filter FilterCraftsman init");
    }

    public void destroy() {
        logger.info("filer FilterCraftsman destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("filter FilterCraftsman doFilter started");
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        Craftsman craftsman = (Craftsman) session.getAttribute("entityCraftsman");
        if(craftsman != null) {
            logger.info("filter FilterCraftsman doFilter craftsman = {}", craftsman);
            chain.doFilter(request, response);
        } else {
            logger.warn("craftsman = null in filter FilterCraftsman. Send redirect to /autorisation-craftsmen");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/autorisation-craftsmen");
            requestDispatcher.forward(request, response);
        }
    }
}
