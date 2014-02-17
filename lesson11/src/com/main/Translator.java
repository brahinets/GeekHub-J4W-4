package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("translator")
public class Translator {

    @Autowired
	private Dictionary dictionary;
    @Autowired
	private LanguageDetector languageDetector;

    @PostConstruct
    public void init() {

    }

    public String translate(String text) {
		Language language = languageDetector.detectLanguage(text);
		String[] words = text.split(" ");
		StringBuilder sb = new StringBuilder();

        for (String word : words) {
			String translatedWord = dictionary.translate(word, language);
			sb.append(translatedWord + " ");
		}

		return sb.toString();
	}
}
