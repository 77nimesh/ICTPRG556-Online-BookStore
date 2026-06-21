/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 * 
 * @author 77nim
 * Dispatcher for updating quantities and removing books from the cart.
 */
public class UpdateCartDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            return config.getInitParameter("view.titles");
        }

        String[] booksToRemove = request.getParameterValues("remove");

        if (booksToRemove != null) {
            for (int i = 0; i < booksToRemove.length; i++) {
                cart.remove(booksToRemove[i]);
            }
        }

        if (cart.isEmpty()) {
            session.removeAttribute("cart");
            return config.getInitParameter("view.titles");
        }

        Set<Map.Entry<String, CartItem>> entries = cart.entrySet();
        Iterator<Map.Entry<String, CartItem>> iter = entries.iterator();

        while (iter.hasNext()) {
            Map.Entry<String, CartItem> entry = iter.next();
            String isbn = entry.getKey();
            CartItem item = entry.getValue();

            String quantityValue = request.getParameter(isbn);

            if (quantityValue != null) {
                int quantity = Integer.parseInt(quantityValue);
                item.updateQuantity(quantity);
            }
        }

        session.setAttribute("cart", cart);
        return config.getInitParameter("view.cart");
    }
}
