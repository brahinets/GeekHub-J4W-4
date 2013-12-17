package org.geekhub;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ImageCrawler downloads all images to specified folder from specified resource.
 * It uses multi threading to make process faster. To start download images you should call downloadImages(String urlToPage) method with URL.
 * To shutdown the service you should call stop() method
 */
public class ImageCrawler {

    //number of threads to download images simultaneously
    public static final int NUMBER_OF_THREADS = 15;

    private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private String folder;

    public ImageCrawler(String folder) throws MalformedURLException {
        this.folder = folder;
    }

    /**
     * Call this method to start download images from specified URL.
     * @param urlToPage
     * @throws IOException
     */
    public void downloadImages(String urlToPage) throws IOException {
        Page curPage = new Page(new URL(urlToPage));
        Collection<URL> linksToImages = curPage.getImageLinks();

        for(URL imageUrl: linksToImages )     {
            if(isImageURL(imageUrl)){
                ImageTask task = new ImageTask(imageUrl , this.folder);
                executorService.execute(task);
            }
        }
    }

    /**
     * Call this method before shutdown an application
     */
    public void stop() {
        executorService.shutdown();
    }

    /**
     * Detects is current url is an image. Checking for popular extensions should be enough
     */
    private boolean isImageURL(URL url) {
        Pattern imgPattern = Pattern.compile("(https?:\\/\\/.*\\.(?:bmp|gif|jpg|jpeg|png))$", Pattern.CASE_INSENSITIVE);
        Matcher imgMatch = imgPattern.matcher(url.toString());

        return imgMatch.matches();
    }
}
