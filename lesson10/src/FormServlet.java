import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name="notemanager", urlPatterns={"/notemanager/*"})
public class FormServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher view = null;
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String value = request.getParameter("value");

        if(action != null && action.equals("remove") && id != null){
            NotesManager.removeNote(Integer.parseInt(id));
        } else {
            if(name != null && name.length() != 0 && value != null && value.length() != 0) {
                NotesManager.addNote(name, value, new Date());
            }
        }

        session.setAttribute("notesList", NotesManager.notes);
        view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}