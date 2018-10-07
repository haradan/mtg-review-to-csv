package com.github.haradan.mtgtocsv;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LSVSetReviewCardTest {

	@Test
	public void canLoadAerialEngineer() throws Exception {
		LSVSetReviewCard card = LSVSetReviewCard.builder()
				.title("Aerial Engineer")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("U");
		assertThat(card.getColourCode()).isEqualTo("WU");
	}

	@Test
	public void canLoadMagistratesScepter() throws Exception {
		LSVSetReviewCard card = LSVSetReviewCard.builder()
				.title("Magistrate’s Scepter")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("R");
		assertThat(card.getColourCode()).isEqualTo("");
	}

	@Test
	public void canLoadDragonsHoard() throws Exception {
		LSVSetReviewCard card = LSVSetReviewCard.builder()
				.title("Dragon’s Hoard")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("R");
		assertThat(card.getColourCode()).isEqualTo("");
	}

	@Test
	public void canLoadTectonicRift() throws Exception {
		LSVSetReviewCard card = LSVSetReviewCard.builder()
				.title("Tectonic Rift")
				.loadAPIData().build();

		assertThat(card.getRarityCode()).isEqualTo("U");
		assertThat(card.getColourCode()).isEqualTo("R");
	}

	@Test
	public void canLoadMultipleCards() throws Exception {
		LSVSetReviewCard.LSVSetReviewCardBuilder hoardB = LSVSetReviewCard.builder()
				.title("Dragon’s Hoard");
		LSVSetReviewCard.LSVSetReviewCardBuilder scepterB = LSVSetReviewCard.builder()
				.title("Magistrate’s Scepter");
		LSVSetReviewCard.LSVSetReviewCardBuilder riftB = LSVSetReviewCard.builder()
				.title("Tectonic Rift");

		LSVSetReviewCard.loadAPIData(null, Arrays.asList(hoardB, scepterB, riftB));

		LSVSetReviewCard hoard = hoardB.build();
		LSVSetReviewCard scepter = scepterB.build();
		LSVSetReviewCard rift = riftB.build();

		assertThat(hoard.getRarityCode()).isEqualTo("R");
		assertThat(hoard.getColourCode()).isEqualTo("");
		assertThat(scepter.getRarityCode()).isEqualTo("R");
		assertThat(scepter.getColourCode()).isEqualTo("");
		assertThat(rift.getRarityCode()).isEqualTo("U");
		assertThat(rift.getColourCode()).isEqualTo("R");
	}
}
