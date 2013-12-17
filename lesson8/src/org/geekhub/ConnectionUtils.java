package org.geekhub;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Utils class that contains useful method to interact with URLConnection
 */
public class ConnectionUtils {

    /**
     * Downloads content for specified URL and returns it as a byte array.
     * Should be used for small files only. Don't use it to download big files it's dangerous.
     * @param url
     * @return
     * @throws IOException
     */
    public static byte[] getData(URL url) throws IOException {
        ByteArrayOutputStream pageData = new ByteArrayOutputStream();
        byte[] dataPortion = new byte[1024];
        int n;

        try (InputStream in = new BufferedInputStream(url.openStream())){
            while ((n = in.read(dataPortion )) > 0) {
                pageData.write(dataPortion, 0, n);
            }
        } catch (IOException e) {
            System.err.printf("Reading failed : " + e.getMessage());
        }

        return pageData.toByteArray();
    }
}