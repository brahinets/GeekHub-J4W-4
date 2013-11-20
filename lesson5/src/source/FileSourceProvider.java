/**
 * Class: FileSourceProvider
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 12:35
 * Mail: ysb.kanivtsi@gmail.com
 */


package source;

import java.io.*;

/**
 * Implementation for loading content from local file system.
 * This implementation supports absolute paths to local file system without specifying file:// protocol.
 * Examples c:/1.txt or d:/pathToFile/file.txt
 */
public class FileSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) throws IOException {
        try {
            FileInputStream ifStream = new FileInputStream(pathToSource);
            if(Helper.getExtension(pathToSource).equals("txt")){
                return true;
            } else {
                return false;
            }
        } catch (IOException e){
            return false;
        }
    }


    @Override
    public String load(String pathToSource) throws IOException{
        StringBuilder text = new StringBuilder();
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(pathToSource));

        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        } catch (IOException ex){
        }

        if(text.toString().isEmpty()){
            throw new IOException();
        }

        return text.toString();
    }
}