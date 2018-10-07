package com.github.haradan.mtgtocsv;

import com.github.haradan.mtgtocsv.mtgio.Set;
import com.github.haradan.mtgtocsv.mtgio.SetsAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class LSVSetReview {

	private final List<LSVSetReviewCard> cards;

	public static LSVSetReview fromColourURLs(String... urls) throws IOException, SAXException {

		List<Set> sets = SetsAPI.loadSets();

		List<LSVSetReviewCard> cards = new ArrayList<>();
		for(String url : urls)
			addCards(url, sets, cards);

		return new LSVSetReview(cards);
	}

	public void outputToCSV(File csv) throws IOException, SAXException {
		FileUtils.forceMkdirParent(csv);
		try(CSVPrinter printer = CSVFormat.DEFAULT.withRecordSeparator("\n").print(new FileWriter(csv))) {
			printer.printRecord("Card Name", "Colour", "Rarity", "Rating", "Alt Rating", "Extra Rating", "Raw Ratings", "Review");

			for(LSVSetReviewCard card : cards) {
				LSVCardRatings ratings = card.getRatings();
				String[] ratingsArr = ratings.getNRatingsArr(3);

				printer.printRecord(card.getTitle(), card.getColourCode(), card.getRarityCode(),
						ratingsArr[0], ratingsArr[1], ratingsArr[2], ratings.getRawRatingsStr(), card.getReview());
			}
		}
	}

	private static void addCards(String url, List<Set> sets, List<LSVSetReviewCard> cards) throws IOException, SAXException {
		Document doc = Jsoup.connect(url).get();

		Element postTitle = doc.selectFirst(".postTitle");
		String postTitleStr = postTitle.text();
		Set postSet = null;
		for(Set set : sets) {
			if(! set.isMainRelease())
				continue;
			if(postTitleStr.contains(set.getName()))
				postSet = set;
		}

		Element content = doc.selectFirst(".postContent");
		Elements headers = content.select("h1");
		List<LSVSetReviewCard.LSVSetReviewCardBuilder> builders = new ArrayList<>();
		for(Element header : headers) {
			Element imgDiv = header.nextElementSibling();
			if(imgDiv == null || ! imgDiv.is("div"))
				continue;

			Element ratingHeader = imgDiv.nextElementSibling();
			if(ratingHeader == null || ! ratingHeader.is("h3"))
				continue;

			List<Element> ratingHeaders = new ArrayList<>();
			do {
				ratingHeaders.add(ratingHeader);
				ratingHeader = ratingHeader.nextElementSibling();
			} while(ratingHeader != null && ratingHeader.is("h3"));

			Element reviewP = ratingHeader;

			if(reviewP == null || !reviewP.is("p"))
				continue;

			StringBuilder reviewB = new StringBuilder();
			do {
				reviewB.append(reviewP.text());
				reviewP = reviewP.nextElementSibling();
			} while(reviewP != null && reviewP.is("p"));

			String title = header.text();
			String review = reviewB.toString();

			LSVCardRatings ratings = getRatings(ratingHeaders);

			LSVSetReviewCard.LSVSetReviewCardBuilder builder = LSVSetReviewCard.builder()
					.title(title)
					.ratings(ratings).review(review);
			builders.add(builder);
		}

		LSVSetReviewCard.loadAPIData(postSet, builders);

		for(LSVSetReviewCard.LSVSetReviewCardBuilder builder : builders) {
			cards.add(builder.build());
		}
	}

	private static LSVCardRatings getRatings(List<Element> headers) {
		List<LSVCardRating> ratings = new ArrayList<>(headers.size());
		for(Element header : headers) {
			LSVCardRating rating = LSVCardRating.parse(header.text());
			ratings.add(rating);
		}
		return new LSVCardRatings(ratings);
	}

}
