import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by Yarik on 25.01.14.
 *
 * Realizing possible actions with file explorer
 *
 */
public class Actions {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter out;
    private String rootDir;
    private String path;

    public Actions (HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request =  request;
        this.response =  response;
        this.out = response.getWriter();
        this.path = request.getParameter("path");
        this.rootDir = "C:/";
    }


    /**
     * Main method
     * Generate HTML page with required actions content (LIST, EDIT, DELETE, CREATE)
     *
     * @throws ServletException
     * @throws IOException
     */
    public void doWork() throws ServletException, IOException {
        String title = "Commander";

        out.print("<!DOCTYPE html>" +
                    "<html>" +
                        "<head>" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                            "<title>" + title + "</title>" +
                        "</head>" +
                        "<body>"
        );

        if(path == null || path.length()==0 || !new File(path).exists()){
            path = rootDir;
        }

        printLinksToAllDrives();
        printLinkToParentDirectory();

        if(request.getParameter("action") == null) {
            show();
        } else {
            switch (request.getParameter("action")) {
                case  "createFile"  :
                    FileUtils.createFile(path, request.getParameter("newFileName"));
                    response.sendRedirect("/commander?path=" + path);
                    break;
                case  "createDir"  :
                    FileUtils.createDir(path, request.getParameter("newFileName"));
                    response.sendRedirect("/commander?path=" + path);
                    break;
                case  "delete"  : delete(); break;
                case  "edit"    : editFile(); break;
                case  "open"    : show();   break;
                default         : show();   break;
            }
        }

        out.print(    "</body>" +
                "</html>"
        );
    }



    /**
     * Generate HTML to show content of directory or file (if it readable)
     *
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws IOException, ServletException {
        File folder = new File(path);

        /* if it readable file (and not directory), then return it's content */
        if(!(new File(path)).isDirectory()){
            out.print(FileUtils.readFileAndAddHTMLtags(path));
            return;
        }

        printFileCreationForm();
        printDirCreationForm();

        /* else print content of directory */
        /* firstly print all directories */
        out.print("<table>" +
                "<tr>" +
                    "<td>Name</td>" +
                    "<td>Size</td>" +
                    "<td>Last Modified</td>"+
                    "<td></td>" + /* for remove icon*/
                    "<td></td>" + /* for edit icon*/
                "</tr>"
        );
        for (File exemplar : folder.listFiles()) {
            if(exemplar.isDirectory() && exemplar.list()!=null){ // check for NULL, because not work with DocANDsettings and SysVolumeInfo
                out.print("<tr>" +
                            "<td>" +
                                "<a href=commander?path=" + Utils.encodeText(exemplar.getAbsolutePath()) + "&action=open>" + "<b>" + exemplar.getName() + "</b></a>" +
                            "</td>" +
                            "<td>" +
                                "DIR" +
                            "</td>" +
                            "<td>" +
                                new Date(exemplar.lastModified()) +
                            "</td>" +
                            "<td>" +
                                "<a href=commander?path=" + Utils.encodeText(exemplar.getAbsolutePath()) + "&action=delete>" + "<img src=\"img/delete.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                            "</td>" +
                        "</tr>");
            }
        }

        /* secondly print all files */
        for (File exemplar : folder.listFiles()) {
            String sizeType = "KB";

            if(exemplar.isFile()){
                out.print("<tr>" +
                            "<td>" +
                                "<a href=commander?path=" + Utils.encodeText(exemplar.getAbsolutePath()) + "><i>" + exemplar.getName() + "</i></a>" +
                            "</td>" +
                            "<td>" +
                                FileUtils.getFileSize(exemplar, sizeType) + " " + sizeType + "      " +
                            "</td>" +
                            "<td>" +
                                new Date(exemplar.lastModified()) +
                            "</td>" +
                            "<td>" +
                                "<a href=commander?path=" + Utils.encodeText(exemplar.getAbsolutePath()) + "&action=delete>" + "<img src=\"img/delete.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                            "</td>"
                );
                if(FileUtils.isEditable(exemplar.getAbsolutePath())){
                    out.print("<td>" +
                                "<a href=commander?path=" + Utils.encodeText(exemplar.getAbsolutePath()) + "&action=edit>" + "<img src=\"img/edit.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                            "</td>"
                    );
                }
                out.print("</tr>");
            }
        }
        out.print("</table>");
    }


    /**
     * Edit file (now supported just TXT)
     *
     * @throws ServletException
     * @throws IOException
     */
    public void editFile() throws ServletException, IOException {
        if(!FileUtils.isEditable(path)){
            response.sendRedirect("/commander?path="+rootDir);
        }
        switch (FileUtils.getFileExtension(path)){
            case "txt":
                String text = request.getParameter("text");

                if(text != null){
                    PrintWriter writer = new PrintWriter(path, "UTF-8");

                    writer.print(text);
                    writer.close();
                    response.sendRedirect("/commander?path=" + path + "&action=open");
                } else {
                    out.print(Utils.makeMessage(path) +
                            "<form  method=\"POST\" action=\"/commander\">" +
                                "<input hidden name=\"action\" value=\"edit\">" +
                                "<input hidden name=\"path\" value=\""+path+"\">" +
                                "<textarea name=\"text\" rows=\"25\" cols=\"120\">" + FileUtils.readTXT(path, "\n") + "</textarea> <br>" +
                                "<input type=\"submit\" value=\"Save\">" +
                            "</form>"
                    );
                }
                break;
            default:
                response.sendRedirect("/commander?path=/");
        }
    }


    /**
     * Delete file or dir with path
     *
     * @throws ServletException
     * @throws IOException
     */
    private void delete() throws ServletException, IOException {
        String path = request.getParameter("path");
        File file = new File(path);
        int endIndex;

        if(file.exists()){
            FileUtils.deleteFolderOrFile(file);

            if((endIndex = path.lastIndexOf("/")) == -1) {
                endIndex = path.lastIndexOf("\\");
            }

            response.sendRedirect("/commander?path=" + path.substring(0, endIndex+1));//to previous folder
        } else {
            response.sendRedirect("/commander?path=" + rootDir);
        }
    }


    private void printFileCreationForm() throws IOException {
        out.print("<form  method=\"GET\" action=\"/commander\">" +
                    "<input hidden name=\"action\" value=\"createFile\">" +
                    "<input hidden name=\"path\" value=\""+path+"\">" +
                    "<input name=\"newFileName\">" +
                    "<input type=\"submit\" value=\"Create File\">" +
                "</form>"
        );
    }

    private void printDirCreationForm() throws IOException {
        out.print("<form  method=\"GET\" action=\"/commander\">" +
                "<input hidden name=\"action\" value=\"createDir\">" +
                "<input hidden name=\"path\" value=\""+path+"\">" +
                "<input name=\"newFileName\">" +
                "<input type=\"submit\" value=\"Create Dir\">" +
                "</form>"
        );
    }


    private void printLinkToParentDirectory() throws IOException {
        File folder = new File(path);

        if(folder.getParent() != null && !(folder.getAbsolutePath()+"\\").equals(rootDir)){
            out.print("<br><a href=\"?path=" + folder.getParent() + "\"> ...GO BACK... </a><br><br>");
        }
    }


    private void printLinksToAllDrives() {
        File[] drives = File.listRoots();

        for(File drive : drives){
            out.print("<a href=\"?path=" + drive.getPath() + "\"> <font size=\"5\" color=\"#48A5D8\" face=\"Arial\">" + drive + "</font></a>     ");
        }
    }
}
