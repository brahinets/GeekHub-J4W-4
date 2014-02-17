package com.main;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceLoader {

	public List<String> load(String source) {
		try {
            return Files.readAllLines(Paths.get(source), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}
}
