import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Yarik on 25.01.14.
 *
 * Helpful utils
 *
 */

public abstract class Utils {

    /**
     * Encodes text to put it as URL parameter
     *
     * @param url adress
     * @return encoded text
     */
    public static String encodeText(String url) throws IOException {
        return URLEncoder.encode(url, "UTF-8").replace("+", "%20");
    }


    /**
     * @param messageText
     * @return message with HTML tags
     */
    public static String makeMessage(String messageText){
        return "<h3><center>" + messageText + "</center></h3>";
    }
}
