import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Yarik on 25.01.14.
 *
 * Helpful utils
 *
 */

public abstract class Utils {

    public static String encodeURL(String url) throws IOException {
        return URLEncoder.encode(url, "UTF-8").replace("+", "%20");
    }


    public static String decodeURL(String url) throws IOException {
        return URLDecoder.decode(url, "UTF-8");
    }


    public static String makeMessage(String messageText){
        return "<h3><center>" + messageText + "</center></h3>";
    }
}
