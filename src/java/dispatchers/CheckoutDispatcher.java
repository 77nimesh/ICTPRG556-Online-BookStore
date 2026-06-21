/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 77nim
 * Dispatcher for displaying the checkout page.
 */
public class CheckoutDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        HttpSession session = request.getSession();
        Map cart = (Map) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return config.getInitParameter("view.titles");
        }

        return config.getInitParameter("view.checkout");
    }
}