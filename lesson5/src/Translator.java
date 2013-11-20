/**
 * Class: URLSourceProvider
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 12:45
 * Mail: ysb.kanivtsi@gmail.com
 */

import source.URLSourceProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Provides utilities for translating texts to russian language.<br/>
 * Uses Yandex Translate API, more information at <a href="http://api.yandex.ru/translate/">http://api.yandex.ru/translate/</a><br/>
 * Depends on {@link URLSourceProvider} for accessing Yandex Translator API service
 */
public class Translator {
    private URLSourceProvider urlSourceProvider;
    /**
     * Yandex Translate API key could be obtained at <a href="http://api.yandex.ru/key/form.xml?service=trnsl">http://api.yandex.ru/key/form.xml?service=trnsl</a>
     * to do that you have to be authorized.
     */
    private static final String YANDEX_API_KEY = "trnsl.1.1.20131116T095739Z.74743334d25cceba.1e3e25220c2afeb9db8b2eea8c3791f8741704aa";
    private static final String TRANSLATION_DIRECTION = "en-ru";

    public Translator(URLSourceProvider urlSourceProvider) {
        this.urlSourceProvider = urlSourceProvider;
    }

    /**
     * Translates text to russian language
     * @param original text to translate
     * @return translated text
     * @throws IOException
     */
    public String translate(String original) throws IOException {
        return parseContent(urlSourceProvider.load(prepareURL(original)));
    }


    /**
     * Prepares URL to invoke Yandex Translate API service for specified text
     * @param text to translate
     * @return url for translation specified text
     */
    private String prepareURL(String text) throws UnsupportedEncodingException {
        return ("https://translate.yandex.net/api/v1.5/tr/translate?key=" + YANDEX_API_KEY + "&text=" + encodeText(text) + "&lang=" + TRANSLATION_DIRECTION);
    }


    /**
     * Parses content returned by Yandex Translate API service. Removes all tags and system texts. Keeps only translated text.
     * @param content that was received from Yandex Translate API by invoking prepared URL
     * @return translated text
     */
    private String parseContent(String content) {
        String parsedAnswer = "";

        int codePlace; // place of answer code
        if((codePlace = content.indexOf("<Translation code=")) != -1){
            int answerCode = Integer.parseInt(content.substring(content.indexOf('"', codePlace + 18) + 1, content.indexOf('"', codePlace + 21)));
            if(answerCode == 200 ){
                parsedAnswer = content.substring(content.indexOf("<text>") + 6, content.indexOf("</text>"));
            }
        }
        return parsedAnswer;
    }


    /**
     * Encodes text that need to be translated to put it as URL parameter
     * @param text to be translated
     * @return encoded text
     */
    private String encodeText(String text) throws UnsupportedEncodingException {
         return URLEncoder.encode(text,"UTF-8").replace("+","%20");
    }
}