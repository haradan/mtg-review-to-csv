package com.github.haradan.mtgtocsv;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Set {

	private final String name;
	private final String code;
	private final boolean isCore;
	private final boolean isExpansion;

	public boolean isMainRelease() {
		return isCore || isExpansion;
	}

}
