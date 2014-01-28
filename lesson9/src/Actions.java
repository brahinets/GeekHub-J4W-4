import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by Yarik on 25.01.14.
 *
 * Realizing possible actions with file explorer
 *
 */
public class Actions {
    private final boolean isOnlyServerFolder;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter out;
    private String rootDir = "C:/";
    private String path;

    public Actions (HttpServletRequest request, HttpServletResponse response, boolean isOnlyServerFolder) throws IOException {
        this.request =  request;
        this.response =  response;
        this.out = response.getWriter();
        this.path = request.getParameter("path");
        this.isOnlyServerFolder = isOnlyServerFolder;
        if(isOnlyServerFolder){
            this.rootDir = request.getServletContext().getRealPath("/");
        }
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
        String action = request.getParameter("action");

        out.print("<!DOCTYPE html>" +
                "<html>" +
                    "<head>" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                        "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>"
        );

        if(this.path == null || !new File(this.path).exists()){
            this.path = rootDir;
        }


        if(action == null) {
            action = "open";
        }

        if(!isOnlyServerFolder){
            printLinksToAllDrives();
        }

        printLinkToParentDirectory();

        switch (action) {
            case  "createFile"  :
                FileUtils.createFile(this.path, request.getParameter("newFileName"));
                response.sendRedirect("/commander?path=" + Utils.encodeURL(this.path));
                break;
            case  "createDir"  :
                FileUtils.createDir(this.path, request.getParameter("newDirName"));
                response.sendRedirect("/commander?path=" + Utils.encodeURL(this.path));
                break;
            case  "delete"  : delete(); break;
            case  "edit"    : editFile(); break;
            case  "open"    : show();   break;
            default         : show();   break;
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
        if(!folder.isDirectory()){
            out.print(FileUtils.readFileAndAddHTMLtags(path));
            return;
        }

        printFileCreationForm();
        printDirCreationForm();

        /* else print content of directory */
        if(FileUtils.isFolderEmpty(folder)){
            File[] folderContent = folder.listFiles();

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
            for (File exemplar : folderContent) {
                if(exemplar.isDirectory()){
                    out.print("<tr>" +
                            "<td>" +
                            "<a href=commander?path=" + Utils.encodeURL(exemplar.getAbsolutePath()) + "&action=open>" + "<b>" + exemplar.getName() + "</b></a>" +
                            "</td>" +
                            "<td>" +
                            "DIR" +
                            "</td>" +
                            "<td>" +
                            new Date(exemplar.lastModified()) +
                            "</td>" +
                            "<td>" +
                            "<a href=commander?path=" + Utils.encodeURL(exemplar.getAbsolutePath()) + "&action=delete>" + "<img src=\"img/delete.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                            "</td>" +
                            "</tr>");
                }
            }

            /* secondly print all files */
            for (File exemplar : folderContent) {
                String sizeType = "KB";

                if(exemplar.isFile()){
                    out.print("<tr>" +
                            "<td>" +
                            "<a href=commander?path=" + Utils.encodeURL(exemplar.getAbsolutePath()) + "><i>" + exemplar.getName() + "</i></a>" +
                            "</td>" +
                            "<td>" +
                            FileUtils.getFileSize(exemplar.getAbsolutePath(), sizeType) + " " + sizeType + "      " +
                            "</td>" +
                            "<td>" +
                            new Date(exemplar.lastModified()) +
                            "</td>" +
                            "<td>" +
                            "<a href=commander?path=" + Utils.encodeURL(exemplar.getAbsolutePath()) + "&action=delete>" + "<img src=\"img/delete.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                            "</td>"
                    );
                    if(FileUtils.isFileEditable(exemplar.getAbsolutePath())){
                        out.print("<td>" +
                                "<a href=commander?path=" + Utils.encodeURL(exemplar.getAbsolutePath()) + "&action=edit>" + "<img src=\"img/edit.png\"  width=\"20px\" height=\"20px\"/>" + "</a>" +
                                "</td>"
                        );
                    }
                    out.print("</tr>");
                }
            }
            out.print("</table>");
        } else {
            out.print(Utils.makeMessage("Folder '" + path + "' is empty"))  ;
        }

    }


    /**
     * Edit file (now supported just TXT)
     *
     * @throws ServletException
     * @throws IOException
     */
    public void editFile() throws ServletException, IOException {
        String filePath = request.getParameter("path");

        if(!FileUtils.isFileEditable(filePath)){
            response.sendRedirect("/commander?path=" + Utils.encodeURL(rootDir));
            return;
        }

        switch (FileUtils.getFileExtension(filePath)){
            case "txt":
                String text = request.getParameter("text");

                if(text != null){
                    PrintWriter writer = new PrintWriter(filePath, "UTF-8");

                    writer.print(text);
                    writer.close();
                    response.sendRedirect("/commander?path=" + Utils.decodeURL(filePath) + "&action=open");
                } else {
                    out.print(Utils.makeMessage(URLDecoder.decode(path)) +
                            "<form  method=\"POST\" action=\"/commander\">" +
                            "<input hidden name=\"action\" value=\"edit\">" +
                            "<input hidden name=\"path\" value=\"" + filePath + "\">" +
                            "<textarea name=\"text\" rows=\"25\" cols=\"120\">" + FileUtils.readTXT(filePath, "\n") + "</textarea> <br>" +
                            "<input type=\"submit\" value=\"Save\">" +
                            "</form>"
                    );
                }
                break;
            default:
                response.sendRedirect("/commander?path=" + Utils.encodeURL(rootDir));
        }
    }


    /**
     * Delete file or dir with path
     *
     * @throws ServletException
     * @throws IOException
     */
    private void delete() throws ServletException, IOException {
        String filePath = request.getParameter("path");
        File file = new File(filePath);
        String folderToRedirect = rootDir;
        int endIndex;

        if(file.exists()){
            FileUtils.deleteFolderOrFile(filePath);

            if((endIndex = filePath.lastIndexOf("/")) == -1) {
                endIndex = filePath.lastIndexOf("\\");
            }

            folderToRedirect = filePath.substring(0, endIndex+1);
        }

        response.sendRedirect("/commander?path=" + Utils.encodeURL(folderToRedirect));
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
                "<input name=\"newDirName\">" +
                "<input type=\"submit\" value=\"Create Dir\">" +
                "</form>"
        );
    }


    private void printLinkToParentDirectory() throws IOException {
        File folder = new File(path);

        if(isOnlyServerFolder){
            if(folder.getParent() != null && !rootDir.equals(folder.getAbsolutePath()+"\\")){
                out.print("<br><a href=\"?path=" + Utils.encodeURL(folder.getParent()) + "\"> ...GO BACK... </a><br><br>");
            }
        } else {
            if(folder.getParent() != null){
                out.print("<br><a href=\"?path=" + Utils.encodeURL(folder.getParent()) + "\"> ...GO BACK... </a><br><br>");
            }
        }
    }


    private void printLinksToAllDrives() throws IOException {
        File[] drives = File.listRoots();

        for(File drive : drives){
            out.print("<a href=\"?path=" + Utils.encodeURL(drive.getPath()) + "\"> <font size=\"5\" color=\"#48A5D8\" face=\"Arial\">" + drive + "</font></a>     ");
        }
    }
}