/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 77nim
 * Interface used by all dispatcher command classes.
 */
public interface IDispatcher {

    /**
     * Executes the selected user action and returns the next JSP page.
     *
     * @param request the HTTP request
     * @param config servlet configuration containing view page paths
     * @return the JSP page to forward to
     * @throws Exception if processing fails
     */
    public String execute(HttpServletRequest request, ServletConfig config) throws Exception;
}
