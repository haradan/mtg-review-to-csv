package com.github.haradan.mtgtocsv;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SetReviewCardTest {

	@Test
	public void canLoadAerialEngineer() throws Exception {
		SetReviewCard card = SetReviewCard.builder()
				.title("Aerial Engineer")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("U");
		assertThat(card.getColourCode()).isEqualTo("WU");
	}

	@Test
	public void canLoadMagistratesScepter() throws Exception {
		SetReviewCard card = SetReviewCard.builder()
				.title("Magistrate’s Scepter")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("R");
		assertThat(card.getColourCode()).isEqualTo("");
	}

	@Test
	public void canLoadDragonsHoard() throws Exception {
		SetReviewCard card = SetReviewCard.builder()
				.title("Dragon’s Hoard")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("R");
		assertThat(card.getColourCode()).isEqualTo("");
	}

	@Test
	public void canLoadTectonicRift() throws Exception {
		SetReviewCard card = SetReviewCard.builder()
				.title("Tectonic Rift")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("U");
		assertThat(card.getColourCode()).isEqualTo("R");
	}

	@Test
	public void canLoadMultipleCards() throws Exception {
		SetReviewCard.SetReviewCardBuilder hoardB = SetReviewCard.builder()
				.title("Dragon’s Hoard");
		SetReviewCard.SetReviewCardBuilder scepterB = SetReviewCard.builder()
				.title("Magistrate’s Scepter");
		SetReviewCard.SetReviewCardBuilder riftB = SetReviewCard.builder()
				.title("Tectonic Rift");

		SetReviewCard.loadAPIData(null, Arrays.asList(hoardB, scepterB, riftB));

		SetReviewCard hoard = hoardB.build();
		SetReviewCard scepter = scepterB.build();
		SetReviewCard rift = riftB.build();

		assertThat(hoard.getRarityCode()).isEqualTo("R");
		assertThat(hoard.getColourCode()).isEqualTo("");
		assertThat(scepter.getRarityCode()).isEqualTo("R");
		assertThat(scepter.getColourCode()).isEqualTo("");
		assertThat(rift.getRarityCode()).isEqualTo("U");
		assertThat(rift.getColourCode()).isEqualTo("R");
	}
}
