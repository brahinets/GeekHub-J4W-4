package com.main;

import com.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dictionary {

    public static String dictionariesDirPath;

    @Autowired
    private ResourceLoader resourceLoader;

	private Map<Language, Map<String, String>> dictionaries = new HashMap<Language, Map<String, String>>();

	public String translate(String word, Language language) {
		Map<String, String> dictionary = getDictionary(language);
		String translation = dictionary.get(word);

        return translation == null ? word : translation;
	}

	private Map<String, String> getDictionary(Language language) {
		Map<String, String> dictionary = dictionaries.get(language);

        if (null == dictionary) {
			dictionary = loadDictionary(language);
			dictionaries.put(language, dictionary);
		}

        return dictionary;
	}


	private Map<String, String> loadDictionary(Language language) {
		Map<String, String> dict = new HashMap<String, String>();
        List<String> lines = resourceLoader.load(this.dictionariesDirPath + "/" + language.name().toLowerCase() + ".dict");

        for (String line : lines) {
			String[] parts = line.split("=");
			dict.put(parts[0], parts[1]);
		}

        return dict;
	}
}
