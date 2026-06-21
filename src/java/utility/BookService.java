/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Book;

/**
 * 
 * @author 77nim
 * BookService uses the JPA Persistence Unit to retrieve book records.
 */
public class BookService {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("BookShopPU");

    /**
     * Reads all books from the TBOOKS table using JPA.
     *
     * @return list of books
     */
    public List<Book> findAllBooks() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createNamedQuery("Book.findAll", Book.class).getResultList();
        } finally {
            em.close();
        }
    }
}
