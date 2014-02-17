package com.lang;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LanguageDetector {

	private Map<Language, List<String>> mapping;


	public Language detectLanguage(String text) {
		if (mapping == null) {
			initMapping();
		}

        for (Language language : mapping.keySet()) {
			//Some weird logic to detect language
		}

		return Language.ENGLISH;
	}

	private void initMapping() {
		mapping = new HashMap<Language, List<String>>();
	}
}
