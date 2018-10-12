package com.github.haradan.mtgtocsv;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

public class LSVSetReviewToCSVTest {

	@Test
	public void canConvertCore19LtdBlueToCSV() throws Exception {

		LSVSetReview review = LSVSetReview.fromColourURLs(
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-blue/");

		File file = new File("test/m19-ltd-blue.csv");
		review.outputToCSV(file);

		String csv = FileUtils.readFileToString(file, Charset.defaultCharset());

		String[] lines = csv.split("\n");
		assertThat(lines[0]).isEqualTo("Card Name,Colour,Rarity,Rating,Alt Rating,Extra Rating,Raw Ratings,Review");
		assertThat(lines[1]).isEqualTo("Aether Tunnel,U,U,2.5,,,Limited: 2.5,\"This is an neat finisher for aggressive blue decks—it basically guarantees one hit, and if it isn’t answered it clocks the opponent quickly. I’m glad it’s an uncommon, as the joke would get old at common. Not every deck will want this, but those that do will take it as if it were a 3.0 or 3.5—it’s situational but quite powerful.\"");
		assertThat(lines[2]).isEqualTo("Anticipate,U,C,2.0,,,Limited: 2.0,\"Like Opt, this is the kind of card that will often end up on the chopping block because it doesn’t do anything. It’s a good way to spend turn 2 in a deck without low drops, and it smooths out your draws, but you won’t always have space for it. Unlike Opt in Dominaria, there isn’t a heavy spells-matter component, which is why Opt ended up being much stronger in that format. I anticipate playing this most of the time.\"");
		assertThat(lines[37]).isEqualTo("\"Tezzeret, Artifice Master\",U,M,4.5,,,Limited: 4.5,\"Making a Thopter each turn is a nice ability, and Tezzeret starts with enough loyalty that you’ll frequently be able to take the first hit and build up a blocking force. He can even draw extra cards if 1/1 flyers won’t do the trick, and the ultimate is a real thing if they can’t attack his loyalty.\"");
		assertThat(csv).hasLineCount(43);
	}

	@Test
	public void canConvertCore19LtdGoldToCSV() throws Exception {

		LSVSetReview review = LSVSetReview.fromColourURLs(
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-gold-artifacts-and-lands/");

		File file = new File("test/m19-ltd-gold.csv");
		review.outputToCSV(file);

		String csv = FileUtils.readFileToString(file, Charset.defaultCharset());

		String[] lines = csv.split("\n");
		assertThat(lines[0]).isEqualTo("Card Name,Colour,Rarity,Rating,Alt Rating,Extra Rating,Raw Ratings,Review");
		assertThat(lines[1]).isEqualTo("Aerial Engineer,WU,U,3.0,,,Limited: 3.0,\"Aerial Engineer is a solid body by itself, though not one you’d play without the upside. If you have 2-3 artifacts in your deck, this card is good, and at 4+ it becomes one of your better cards (and I’d adjust the rating up accordingly).\"");
		assertThat(csv).hasLineCount(42);
	}

	@Test
	public void canConvertCore19LtdRedToCSV() throws Exception {

		LSVSetReview review = LSVSetReview.fromColourURLs(
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-red/");

		File file = new File("test/m19-ltd-red.csv");
		review.outputToCSV(file);

		String csv = FileUtils.readFileToString(file, Charset.defaultCharset());

		String[] lines = csv.split("\n");
		assertThat(lines[0]).isEqualTo("Card Name,Colour,Rarity,Rating,Alt Rating,Extra Rating,Raw Ratings,Review");
		assertThat(lines[1]).isEqualTo("Act of Treason,R,C,1.0,3.0,,Limited: 1.0 // 3.0,\"This is one of the more interesting cards in the set. By itself, it’s not great. It’s a narrow card for hyper-aggressive decks, but generally wrong to run. It doesn’t do much unless you’re really pressuring their life total, and isn’t worth a card in most circumstances. Where it gets good is when you’re in the sacrifice deck, usually red-black, and have 3+ ways to sacrifice whatever you steal. Once you have that, this becomes extremely threatening, as it’s Terminate plus deal damage to your opponent, and you even get the benefit of any death triggers their creature may have. I wouldn’t take this early, but it’s the most important payoff in the sacrifice deck, so once you’re deep into that this becomes a very high pick.\"");
		assertThat(lines[35]).isEqualTo("Tectonic Rift,R,U,1.0,,,Limited: 1.0,\"Don’t let the land destruction element fool you. This is a finisher, and you are rarely going to want to snap this off as a land destruction spell on turn 4. I’d rather just have Lava Axe or Act of Treason, but if you miss on all of those, this can close out games. It’s also a fine sideboard card against board stalls or Gift of Paradise, even if it isn’t earth-shaking.\"");
		assertThat(csv).hasLineCount(42);
	}

	@Test
	public void canConvertCore19LtdAllToCSV() throws Exception {

		LSVSetReview review = LSVSetReview.fromColourURLs(
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-gold-artifacts-and-lands/",
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-green/",
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-red/",
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-black/",
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-blue/",
				"https://www.channelfireball.com/articles/core-set-2019-limited-set-review-white/");

		File file = new File("test/m19-ltd.csv");
		review.outputToCSV(file);

		String csv = FileUtils.readFileToString(file, Charset.defaultCharset());

		String[] lines = csv.split("\n");
		assertThat(lines[0]).isEqualTo("Card Name,Colour,Rarity,Rating,Alt Rating,Extra Rating,Raw Ratings,Review");
		assertThat(lines[1]).isEqualTo("Aerial Engineer,WU,U,3.0,,,Limited: 3.0,\"Aerial Engineer is a solid body by itself, though not one you’d play without the upside. If you have 2-3 artifacts in your deck, this card is good, and at 4+ it becomes one of your better cards (and I’d adjust the rating up accordingly).\"");
		assertThat(csv).hasLineCount(250);
	}

	@Test
	public void canConvertGuildsOfRavnicaLtdToCSV() throws Exception {

		LSVSetReview review = LSVSetReview.fromColourURLs(
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-white/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-blue/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-black/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-red/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-green/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-boros/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-dimir/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-golgari/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-izzet/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-selesnya/",
				"https://www.channelfireball.com/articles/guilds-of-ravnica-limited-set-review-artifacts-lands-and-guild-ranking/");

		File file = new File("test/guilds-of-ravnica-ltd.csv");
		review.outputToCSV(file);
	}
}
