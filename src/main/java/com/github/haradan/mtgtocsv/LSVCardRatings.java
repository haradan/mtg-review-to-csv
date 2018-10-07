package com.github.haradan.mtgtocsv;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LSVCardRatings {

	private final List<LSVCardRating> ratings;

	public String getRawRatingsStr() {
		List<String> rawRatings = new ArrayList<>(ratings.size());
		for(LSVCardRating rating : ratings)
			rawRatings.add(rating.getRawText());

		return String.join(", ", rawRatings);
	}

	public String[] getNRatingsArr(int n) {
		String[] arr = new String[n];
		int found = 0;
		for(LSVCardRating rating : ratings) {
			for(String text : rating.getRatings()) {
				arr[found] = text;
				found++;
				if(found == n)
					return arr;
			}
		}
		return arr;
	}

}
