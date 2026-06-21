package controller;

import dispatchers.IDispatcher;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FrontController class to handle HTTP requests and responses.
 *
 * This servlet acts as the main controller in the MVC structure. It receives all
 * browser requests, identifies the requested action, delegates the processing to
 * a dispatcher command object, and forwards control to the selected JSP view.
 */
public class FrontController extends HttpServlet {

    private final Map<String, IDispatcher> actions = new HashMap<String, IDispatcher>();

    /**
     * Loads dispatcher command classes from web.xml init parameters.
     *
     * Each init parameter that starts with "action." is treated as a dispatcher
     * configuration entry. For example:
     *
     * action.view_books = dispatchers.ViewBooksDispatcher
     *
     * @param config ServletConfig object containing servlet init parameters
     * @throws ServletException if a dispatcher cannot be loaded
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        Enumeration names = config.getInitParameterNames();

        while (names.hasMoreElements()) {
            String paramName = (String) names.nextElement();

            if (paramName.startsWith("action.")) {
                String actionName = paramName.substring("action.".length());
                String className = config.getInitParameter(paramName);

                try {
                    IDispatcher dispatcher =
                            (IDispatcher) Class.forName(className).newInstance();

                    actions.put(actionName, dispatcher);
                } catch (Exception ex) {
                    throw new ServletException(
                            "Unable to load dispatcher: " + className, ex);
                }
            }
        }
    }

    /**
     * Processes HTTP GET requests by passing them to doPost().
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * Processes HTTP POST requests.
     *
     * The controller gets the requested action, finds the matching dispatcher,
     * executes the dispatcher, and forwards the request to the JSP view returned
     * by that dispatcher.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String requestedAction = request.getParameter("action");

        if (requestedAction == null || requestedAction.trim().length() == 0) {
            requestedAction = "view_books";
        }

        IDispatcher dispatcher = actions.get(requestedAction);
        String nextPage = getServletConfig().getInitParameter("view.error");

        if (nextPage == null || nextPage.trim().length() == 0) {
            nextPage = "/jsp/error.jsp";
        }

        if (dispatcher == null) {
            request.setAttribute("result", "Unknown action: " + requestedAction);
            dispatch(request, response, nextPage);
            return;
        }

        try {
            nextPage = dispatcher.execute(request, getServletConfig());

            if (nextPage == null || nextPage.trim().length() == 0) {
                nextPage = getServletConfig().getInitParameter("view.error");
            }

        } catch (Exception ex) {
            request.setAttribute("result", ex.getMessage());
            nextPage = getServletConfig().getInitParameter("view.error");

            if (nextPage == null || nextPage.trim().length() == 0) {
                nextPage = "/jsp/error.jsp";
            }
        }

        dispatch(request, response, nextPage);
    }

    /**
     * Forwards the request to the selected JSP page.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page JSP page path
     * @throws ServletException if forwarding fails
     * @throws IOException if forwarding fails
     */
    private void dispatch(HttpServletRequest request,
                          HttpServletResponse response,
                          String page)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Returns servlet information.
     *
     * @return servlet description
     */
    @Override
    public String getServletInfo() {
        return "controller.FrontController using Dispatcher Command Pattern";
    }
}