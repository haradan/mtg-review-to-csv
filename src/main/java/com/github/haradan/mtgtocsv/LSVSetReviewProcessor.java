package com.github.haradan.mtgtocsv;

import com.github.haradan.mtgtocsv.mtgio.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LSVSetReviewProcessor {

    public static Stream<LSVSetReviewCard> loadCards(String url, List<Set> sets) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Element postTitle = doc.selectFirst(".postTitle");
        String postTitleStr = postTitle.text();
        Set postSet = null;
        for (Set set : sets) {
            if (!set.isMainRelease()) {
                continue;
            }
            if (postTitleStr.contains(set.getName())) {
                postSet = set;
            }
        }

        Element content = doc.selectFirst(".postContent");
        Elements headers = content.select("h1, h2");
        List<LSVSetReviewCard.LSVSetReviewCardBuilder> builders = new ArrayList<>();
        for (Element header : headers) {
            Element imgDiv = header.nextElementSibling();
            if (imgDiv == null || !imgDiv.is("div")) {
                continue;
            }

            Element ratingHeader = imgDiv.nextElementSibling();
            if (ratingHeader == null || !ratingHeader.is("h3, h4")) {
                continue;
            }

            List<Element> ratingHeaders = new ArrayList<>();
            do {
                ratingHeaders.add(ratingHeader);
                ratingHeader = ratingHeader.nextElementSibling();
            } while (ratingHeader != null && ratingHeader.is("h3, h4"));

            Element reviewP = ratingHeader;

            if (reviewP == null || !reviewP.is("p")) {
                continue;
            }

            StringBuilder reviewB = new StringBuilder();
            do {
                reviewB.append(reviewP.text());
                reviewP = reviewP.nextElementSibling();
            } while (reviewP != null && reviewP.is("p"));

            String title = header.text();
            String review = reviewB.toString();

            LSVCardRatings ratings = getRatings(ratingHeaders);

            LSVSetReviewCard.LSVSetReviewCardBuilder builder = LSVSetReviewCard.builder()
                    .title(title)
                    .ratings(ratings).review(review);
            builders.add(builder);
        }

        LSVSetReviewCard.loadAPIData(postSet, builders);

        return builders.stream()
                .map(LSVSetReviewCard.LSVSetReviewCardBuilder::build);
    }

    private static LSVCardRatings getRatings(List<Element> headers) {
        List<LSVCardRating> ratings = new ArrayList<>(headers.size());
        for (Element header : headers) {
            LSVCardRating rating = LSVCardRating.parse(header.text());
            ratings.add(rating);
        }
        return new LSVCardRatings(ratings);
    }
}
