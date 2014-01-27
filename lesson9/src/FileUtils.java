import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yarik on 25.01.14.
 *
 * Utils need to work with files
 */


public class FileUtils {
    private static Set<String> editableTypes = new HashSet<String>(Arrays.asList(
            "txt"
    ));

    /**
     * Method cases file type and call appropriate method for reading data from file and generate HTML
     *
     * @return data
     * @throws IOException
     */
    public static String readFileAndAddHTMLtags(String path) throws IOException {
        if(!new File(path).exists()){
            return Utils.makeMessage("File not found");
        }

        String name = Utils.makeMessage(path);

        switch (getFileExtension(path)){
            case "txt": return name + readTXT(path, "<br>");
            default   : return "<h5> " + path + "  :  " +
                    getFileSize(new File(path), "KB") +"    KB   :  "+
                    new Date(new File(path).lastModified()) +
                    "</h5>";
        }
    }


    /**
     * Reading content of TXT file and generates HTML code
     *
     * @return
     * @throws IOException
     */
    public static String readTXT(String path, String newLine) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        try {
            String line = br.readLine();

            while (line != null) {
                sb.append(line + newLine);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return sb.toString();
    }


    public static void createFile(String pathToCurrentDir, String fileName) throws ServletException, IOException {
        File file = new File(pathToCurrentDir + "\\" + fileName);

        if(!file.exists()){
            file.createNewFile();
        }
    }


    public static void createDir(String pathToCurrentDir, String dirName) throws ServletException, IOException {
        File file = new File(pathToCurrentDir + "\\" + dirName);

        if(!file.exists()){
            file.mkdirs();
        }
    }


    public static String getFileExtension(String filePath){
        if(filePath.lastIndexOf(".") > -1){
            return filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
        } else {
            return "";
        }
    }


    public static long getFileSize(File file, String size){
        if(file == null && !file.exists()){
            return -1;
        }

        long divisor;

        switch(size) {
            case "KB" : divisor = 1024; break;
            case "MB" : divisor = 1024*1024; break;
            case "GB" : divisor = 1024*1024*1024; break;
            default   : divisor = 1; break;
        }

        return file.length()/divisor;
    }


    public static boolean isEditable(String filePath){
        return editableTypes.contains(getFileExtension(filePath));
    }


    /**
     * recursive deleting of Folder or File
     *
     * @param folder
     */
    public static void deleteFolderOrFile(File folder) {
        File[] filesList = folder.listFiles();

        if(filesList!=null) { //some JVMs return null for empty dirs
            for(File file: filesList) {
                if(file.isDirectory()) {
                    deleteFolderOrFile(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }
}