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
 * Dispatcher for returning to the titles page.
 */
public class ContinueShoppingDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        return config.getInitParameter("view.titles");
    }
}
