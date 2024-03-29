import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yarik on 25.01.14.
 *
 * Simple File Explorer via Servlet (Main class)
 * SERVER or FULL PC   --   change varibale isOnlyServerFolder
 *      -   SERVER = true
 *      -   FULL PC = false
 *
 * possible actions :
 *      - print list of FILES and DIRS in some directory (PATH)
 *      - open supportable files
 *      - delete FILE or DIR
 *      - edit editable file (now just TXT)
 *      - create FILE or DIR
 */

@WebServlet(name="commander", urlPatterns={"/commander/*"})
public class Commander extends HttpServlet {
    boolean isOnlyServerFolder = true;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;");
        response.setCharacterEncoding("UTF-8");

        Actions action = new Actions(request, response, isOnlyServerFolder);

        action.doWork();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}