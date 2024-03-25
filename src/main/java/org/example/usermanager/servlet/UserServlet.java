package org.example.usermanager.servlet;

import org.example.usermanager.dao.IUserDAO;
import org.example.usermanager.dao.UserDAO;
import org.example.usermanager.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserDAO insertUser = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    listUser(req, resp);
                    break;
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        insertUser.deleteUser(id);
        List<User> listUser = insertUser.selectAllUser();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        List<User> userList = insertUser.selectAllUser();
        req.setAttribute("userList", userList);
        requestDispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = insertUser.selectUser(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("edit.jsp");
        req.setAttribute("user", existingUser);
        requestDispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertUsers(req, resp);
                break;
            case "edit":
                updateUsers(req, resp);
                break;
            default:
                break;

        }
    }

    private void updateUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        insertUser.edit(user.getId());
        resp.sendRedirect("/user");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("edit.jsp");
//        requestDispatcher.forward(req,resp);


    }

    private void insertUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(name, email, country);
        insertUser.create(user);
        resp.sendRedirect("/user");
    }
}


