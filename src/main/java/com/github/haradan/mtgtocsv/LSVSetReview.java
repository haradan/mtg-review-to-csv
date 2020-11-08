package com.github.haradan.mtgtocsv;

import com.github.haradan.mtgtocsv.mtgio.Set;
import com.github.haradan.mtgtocsv.mtgio.SetsAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class LSVSetReview {

    private final List<LSVSetReviewCard> cards;

    public static LSVSetReview fromColourURLs(
            CardReviewProcessor processor, String... urls) throws IOException {

        List<Set> sets = SetsAPI.loadSets();

        List<LSVSetReviewCard> cards = new ArrayList<>();
        for (String url : urls) {
            processor.loadCards(url, sets)
                    .forEach(cards::add);
        }

        return new LSVSetReview(cards);
    }

    public static LSVSetReview fromColourURLs(String... urls) throws IOException {
        return fromColourURLs(LSVSetReviewProcessor::loadCards, urls);
    }

    public void outputToCSV(File csv) throws IOException {
        FileUtils.forceMkdirParent(csv);
        try (CSVPrinter printer = CSVFormat.DEFAULT.withRecordSeparator("\n")
                .print(new FileWriter(csv))) {
            printer.printRecord("Card Name", "Colour", "Rarity",
                    "Rating", "Alt Rating", "Extra Rating", "Raw Ratings", "Review");

            for (LSVSetReviewCard card : cards) {
                LSVCardRatings ratings = card.getRatings();
                String[] ratingsArr = ratings.getNRatingsArr(3);

                printer.printRecord(card.getTitle(), card.getColourCode(), card.getRarityCode(),
                        ratingsArr[0], ratingsArr[1], ratingsArr[2], ratings.getRawRatingsStr(),
                        card.getReview());
            }
        }
    }

}
