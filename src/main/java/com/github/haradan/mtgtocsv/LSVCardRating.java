package com.github.haradan.mtgtocsv;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class LSVCardRating {

	private final String rawText;
	private final String name;
	private final List<String> ratings;

	public static LSVCardRating parse(String rawText) {
		int colonPos = rawText.indexOf(": ");
		if(colonPos == -1)
			return new LSVCardRating(rawText, "", Collections.singletonList(rawText));

		String name = rawText.substring(0, colonPos);
		String ratingsStr = rawText.substring(colonPos+2);
		String[] ratings = ratingsStr.split(" // ");
		return new LSVCardRating(rawText, name, Collections.unmodifiableList(Arrays.asList(ratings)));
	}

	public String getFirstRating() {
		if (ratings == null || ratings.size() < 1)
			return null;
		else
			return ratings.get(0);
	}

}
