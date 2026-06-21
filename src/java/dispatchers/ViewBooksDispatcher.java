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
import utility.BookService;

/**
 *
 * @author 77nim
 * Dispatcher for loading all books and displaying the titles page.
 */
public class ViewBooksDispatcher implements IDispatcher {

    /**
     * Loads book records using JPA and stores them in session scope.
     *
     * @param request HTTP request
     * @param config servlet configuration
     * @return titles JSP page
     * @throws Exception if book records cannot be loaded
     */
    public String execute(HttpServletRequest request, ServletConfig config) throws Exception {
        HttpSession session = request.getSession();

        BookService service = new BookService();
        List<Book> books = service.findAllBooks();

        session.setAttribute("books", books);

        return config.getInitParameter("view.titles");
    }
}

