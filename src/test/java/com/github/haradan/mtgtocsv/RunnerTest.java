package com.github.haradan.mtgtocsv;

import java.io.File;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class RunnerTest {

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

  @Test
  public void canConvertDominariaLtdToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-white/",
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-blue/",
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-black/",
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-red/",
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-green/",
        "https://www.channelfireball.com/articles/dominaria-limited-set-review-gold-artifacts-and-lands/");

    File file = new File("test/dominaria-ltd.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertDominariaConstructedToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-white/",
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-blue/",
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-black/",
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-red/",
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-green/",
        "https://www.channelfireball.com/articles/dominaria-constructed-set-review-gold-artifacts-and-lands/");

    File file = new File("test/dominaria-constructed.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertRIXLtdToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-white/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-blue/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-black/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-red/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-green/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-limited-set-review-gold-artifacts-and-lands/");

    File file = new File("test/rix-ltd.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertRIXConstructedToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-white/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-blue/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-black/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-red/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-green/",
        "https://www.channelfireball.com/articles/rivals-of-ixalan-constructed-set-review-gold-artifacts-and-lands/");

    File file = new File("test/rix-constructed.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertIxalanLtdToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-white/",
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-blue/",
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-black/",
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-red/",
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-green/",
        "https://www.channelfireball.com/articles/ixalan-limited-set-review-gold-artifacts-and-lands/");

    File file = new File("test/ixalan-ltd.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertIxalanConstructedToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-white/",
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-blue/",
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-black/",
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-red/",
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-green/",
        "https://www.channelfireball.com/articles/ixalan-constructed-set-review-gold-artifacts-and-lands/");

    File file = new File("test/ixalan-constructed.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertRavnicaAllegianceLtdToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-white/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-blue/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-black/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-red/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-green/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-azorius/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-gruul/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-orzhov/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-simic-and-colorless/",
        "https://www.channelfireball.com/articles/ravnica-allegiance-limited-set-review-rakdos/");

    File file = new File("test/ravnica-allegiance-ltd.csv");
    review.outputToCSV(file);
  }

  @Test
  public void canConvertWarOfTheSparkLtdToCSV() throws Exception {

    LSVSetReview review = LSVSetReview.fromColourURLs(
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-white/",
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-blue/",
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-black/",
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-red/",
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-green/",
        "https://www.channelfireball.com/articles/war-of-the-spark-limited-set-review-gold-artifacts-and-lands/");

    File file = new File("test/war-of-the-spark-ltd.csv");
    review.outputToCSV(file);
  }
}
