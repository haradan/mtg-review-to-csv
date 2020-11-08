package com.github.haradan.mtgtocsv;

import com.github.haradan.mtgtocsv.mtgio.Set;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface CardReviewProcessor {

    Stream<LSVSetReviewCard> loadCards(String url, List<Set> sets) throws IOException;

}
