package com.github.haradan.mtgtocsv;

import com.github.haradan.mtgtocsv.mtgio.CardsAPI;
import com.github.haradan.mtgtocsv.mtgio.Set;
import com.jsoniter.any.Any;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LSVSetReviewCard {

  private final String title;
  private final String colourCode;
  private final String rarityCode;
  private final LSVCardRatings ratings;
  private final String review;

  public static void loadAPIData(Set set,
      List<LSVSetReviewCardBuilder> builders) throws IOException {

    List<String> titles = new ArrayList<>(builders.size());
    Map<String, LSVSetReviewCardBuilder> builderByTitle = new HashMap<>();
    for (LSVSetReviewCardBuilder builder : builders) {
      titles.add(builder.title);
      builderByTitle.put(builder.title, builder);
    }

    Any cards = CardsAPI.loadCards(set, titles);
    for (Any card : cards) {
      String name = card.get("name").toString();
      LSVSetReviewCardBuilder builder = builderByTitle.get(name);
        if (builder == null) {
            continue;
        }

        if (builder.apiData(card)) {
            builderByTitle.remove(name);
        }
    }
  }

  public static class LSVSetReviewCardBuilder {

    public LSVSetReviewCardBuilder title(String title) {
      this.title = title
          .replaceAll("’", "'")
          .replaceAll("Poison Tip Archer", "Poison-Tip Archer")
          .replaceAll("Vaevictis Asmati", "Vaevictis Asmadi");
      return this;
    }

    public LSVSetReviewCardBuilder loadAPIData() throws IOException {
      return loadAPIData(null);
    }

    public LSVSetReviewCardBuilder loadAPIData(Set set) throws IOException {

      Any cards = CardsAPI.loadCards(set, title);
      for (Any card : cards) {
          if (apiData(card)) {
              break;
          }
      }

      return this;
    }

    private boolean apiData(Any card) {
      String rarity = card.get("rarity").toString();
      String rarityCode = rarityToCode(rarity);
        if (rarityCode == null) {
            return false;
        }

      rarityCode(rarityCode);

      StringBuilder colourCodeB = new StringBuilder();
        for (Any code : card.get("colorIdentity")) {
            colourCodeB.append(code.toString());
        }

      colourCode(colourCodeB.toString());
      return true;
    }
  }

  private static String rarityToCode(String rarity) {
    String lower = rarity.toLowerCase();
    if (lower.contains("mythic")) {
      return "M";
    }
    switch (lower) {
      case "rare":
        return "R";
      case "uncommon":
        return "U";
      case "common":
        return "C";
      default:
        return null;
    }
  }

}
