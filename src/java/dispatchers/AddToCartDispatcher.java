/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;

/**
 * 
 * @author 77nim
 * Dispatcher for adding selected books to the shopping cart.
 */
public class AddToCartDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        HttpSession session = request.getSession();

        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");

        if (selectedBooks == null || selectedBooks.length == 0) {
            return config.getInitParameter("view.titles");
        }

        if (cart == null) {
            cart = new HashMap<String, CartItem>();
        }

        for (int i = 0; i < selectedBooks.length; i++) {
            String isbn = selectedBooks[i];
            int quantity = Integer.parseInt(request.getParameter(isbn));

            if (cart.containsKey(isbn)) {
                CartItem item = cart.get(isbn);
                item.setQuantity(quantity);
            } else {
                Book book = getBookFromList(isbn, session);
                CartItem item = new CartItem(book);
                item.setQuantity(quantity);
                cart.put(isbn, item);
            }
        }

        session.setAttribute("cart", cart);
        return config.getInitParameter("view.titles");
    }

    private Book getBookFromList(String isbn, HttpSession session) {
        List<Book> list = (List<Book>) session.getAttribute("books");

        if (list == null) {
            return null;
        }

        for (Book book : list) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }

        return null;
    }
}
