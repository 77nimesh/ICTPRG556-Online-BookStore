/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import utility.AdmitBookStoreDAO;

/**
 *
 * @author 77nim
 * Dispatcher for loading all books and displaying the titles page.
 */
public class ViewBooksDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        HttpSession session = request.getSession();
        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();

        List<Book> books = dao.getAllBooks();
        session.setAttribute("books", books);

        return config.getInitParameter("view.titles");
    }
}

