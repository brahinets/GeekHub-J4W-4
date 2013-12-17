package org.geekhub;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents page that could be downloaded by specified URL.
 * Allows high-level access to page elements.
 */
public class Page {
    Pattern linkPattern = Pattern.compile("<a\\s(?:[^\\s>]*?\\s)*?href=\"(.*?)\".*?>");
    Pattern imageLinkPattern = Pattern.compile("<img.*?src=\"(.*?)\".*?(/>|</img>)");

    private String content;
    private URL url;

    /**
     * Be careful, constructor downloads content, it could be slow.
     * @param url
     * @throws IOException
     */
    public Page(URL url) throws IOException {
        this.url = url;
        this.content = new String(ConnectionUtils.getData(url));
    }

    /**
     * Extracts all links from the page like <a href={link}>bla</a>. Method does not cache content. Each time new list will be returned.
     * @return list of URLs from that page.
     * @throws MalformedURLException
     */
    public Collection<URL> getLinks() throws MalformedURLException {
        return extractMatches(linkPattern.matcher(content));
    }

    /**
     * Extracts all links to images from the page like <img src={link}/>. Method does not cache content. Each time new list will be returned.
     * @return list of URLs to images from that page.
     * @throws MalformedURLException
     */
        public Collection<URL> getImageLinks() throws MalformedURLException {
        return extractMatches(imageLinkPattern.matcher(content));
    }

    /**
     * Extracts all links which satisfy the condition
     * @return list of URLs
     * @throws MalformedURLException
     */
    private Collection<URL> extractMatches(Matcher matcher) throws MalformedURLException {
        Set<URL> links = new HashSet<>();

        while(matcher.find()) {
            links.add(new URL(url, matcher.group(1)));
        }

        return links;
    }
}