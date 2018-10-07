package com.github.haradan.mtgtocsv;

import com.jsoniter.any.Any;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

@Builder
@Getter
public class SetReviewCard {

	private final String title;
	private final String colourCode;
	private final String rarityCode;
	private final LSVCardRatings ratings;
	private final String review;

	public static void loadAPIData(Set set, List<SetReviewCardBuilder> builders) throws IOException {

		List<String> titles = new ArrayList<>(builders.size());
		Map<String, SetReviewCardBuilder> builderByTitle = new HashMap<>();
		for(SetReviewCardBuilder builder : builders) {
			titles.add(builder.title);
			builderByTitle.put(builder.title, builder);
		}

		Any cards = CardsAPI.loadCards(set, titles);
		for(Any card : cards) {
			String name = card.get("name").toString();
			SetReviewCardBuilder builder = builderByTitle.get(name);
			if(builder == null)
				continue;

			if(builder.apiData(card))
				builderByTitle.remove(name);
		}
	}

	public static class SetReviewCardBuilder {

		public SetReviewCardBuilder title(String title) {
			this.title = title
					.replaceAll("’", "'")
					.replaceAll("Poison Tip Archer", "Poison-Tip Archer")
					.replaceAll("Vaevictis Asmati", "Vaevictis Asmadi");
			return this;
		}

		public SetReviewCardBuilder loadAPIData() throws IOException {
			return loadAPIData(null);
		}

		public SetReviewCardBuilder loadAPIData(Set set) throws IOException {

			Any cards = CardsAPI.loadCards(set, title);
			for(Any card : cards) {
				if(apiData(card))
					break;
			}

			return this;
		}

		private boolean apiData(Any card) {
			String rarity = card.get("rarity").toString();
			String rarityCode = rarityToCode(rarity);
			if(rarityCode == null)
				return false;

			rarityCode(rarityCode);

			StringBuilder colourCodeB = new StringBuilder();
			for(Any code : card.get("colorIdentity"))
				colourCodeB.append(code.toString());

			colourCode(colourCodeB.toString());
			return true;
		}
	}

	private static String rarityToCode(String rarity) {
		switch(rarity) {
			case "Mythic Rare":
				return "M";
			case "Rare":
				return "R";
			case "Uncommon":
				return "U";
			case "Common":
				return "C";
			default:
				return null;
		}
	}

}
