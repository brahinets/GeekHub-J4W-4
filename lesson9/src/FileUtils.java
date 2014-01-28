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
        File file = new File(path);

        if(!file.exists() || !file.isFile()){
            return Utils.makeMessage("File not found");
        }

        String name = Utils.makeMessage(path);
        String sizeType = "KB";

        switch (getFileExtension(path)){
            case "txt": return name + readTXT(path, "<br>");
            default   : return "<h5> " + path + "  :  " +
                            getFileSize(path, sizeType) +"    " + sizeType + "   :  "+
                            new Date(file.lastModified()) +
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


    public static boolean createFile(String pathToCurrentDir, String fileName) throws ServletException, IOException {
        return new File(pathToCurrentDir + "\\" + fileName).createNewFile();
    }


    public static boolean createDir(String pathToCurrentDir, String dirName) throws ServletException, IOException {
        return new File(pathToCurrentDir + "\\" + dirName).mkdirs();
    }


    public static String getFileExtension(String filePath){
        if(filePath.lastIndexOf(".") > -1){
            return filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
        } else {
            return "";
        }
    }


    public static long getFileSize(String filePath, String sizeType){
        long divisor;

        switch(sizeType) {
            case "KB" : divisor = 1024; break;
            case "MB" : divisor = 1024*1024; break;
            case "GB" : divisor = 1024*1024*1024; break;
            default   : divisor = 1; break;
        }

        return new File(filePath).length()/divisor;
    }


    public static boolean isFileEditable(String filePath){
        return editableTypes.contains(getFileExtension(filePath))
                && filePath != null
                && new File(filePath).exists();
    }


    public static void deleteFolderOrFile(String path) {
        File folderOrFile = new File(path);
        File[] filesList = folderOrFile.listFiles();

        if(filesList != null) { //some JVMs return null for empty dirs
            for(File subFolder: filesList) {
                if(subFolder.isDirectory()) {
                    deleteFolderOrFile(subFolder.getAbsolutePath());
                } else {
                    subFolder.delete();
                }
            }
        }

        folderOrFile.delete();
    }


    public static boolean isFolderEmpty(File folder){
        if(folder.listFiles() != null && folder.listFiles().length != 0) {
            return true;
        }

        return false;
    }
}