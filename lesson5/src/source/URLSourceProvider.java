/**
 * Class: URLSourceProvider
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 12:20
 * Mail: ysb.kanivtsi@gmail.com
 */

package source;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Implementation for loading content from specified URL.<br/>
 * Valid paths to load are http://someurl.com, https://secureurl.com, ftp://frpurl.com etc.
 */
public class URLSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) {
        URLConnection connection;
        try {
            connection = (new URL(pathToSource)).openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String load(String pathToSource) throws IOException {
        StringBuilder text = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(pathToSource).openStream()));

        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        } catch (IOException ex){
        }

        if(text.toString().isEmpty()) {
            throw new IOException();
        }

        return text.toString();
    }
}
