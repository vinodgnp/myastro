package myastro.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import myastro.constants.Nakshatra;
import myastro.constants.Rashi;

public class AstroCommonService {

	private static final AstroCommonService ASTRO_COMMON_SERVICE = new AstroCommonService();
	private final LinkedHashMap<Rashi, ArrayList<Nakshatra>> starsInRashiCache;
	private final LinkedHashMap<String, String> starsInRashiWithPadamCache;

	public static AstroCommonService getPoruthamUtil() {
		return ASTRO_COMMON_SERVICE;
	}

	private AstroCommonService() {
		starsInRashiCache = getStarsInRashi();
		starsInRashiWithPadamCache = getStarsInRashiWithPadam();

	}

	public LinkedHashMap<Rashi, ArrayList<Nakshatra>> getStarsInRashiCache() {
		return starsInRashiCache;
	}

	public LinkedHashMap<String, String> getStarsInRashiWithPadamCache() {
		return starsInRashiWithPadamCache;
	}

	private LinkedHashMap<Rashi, ArrayList<Nakshatra>> getStarsInRashi() {
		LinkedHashMap<Rashi, ArrayList<Nakshatra>> starsInRashi = new LinkedHashMap<Rashi, ArrayList<Nakshatra>>();

		ArrayList<Nakshatra> starsForRashiList = new ArrayList<Nakshatra>();
		for (Nakshatra nakshatra : Nakshatra.values()) {
			if (nakshatra.getSplit()) {
				starsForRashiList.add(nakshatra);
				starsForRashiList.add(nakshatra);
			} else {
				starsForRashiList.add(nakshatra);
			}
		}

		int index = 0;
		for (Rashi rashi : Rashi.values()) {
			ArrayList<Nakshatra> starInfo = new ArrayList<Nakshatra>();
			for (int i = 0; i < 3; i++) {
				starInfo.add(starsForRashiList.get(index++));
			}
			starsInRashi.put(rashi, starInfo);
		}
		return starsInRashi;
	}

	private LinkedHashMap<String, String> getStarsInRashiWithPadam() {
		LinkedHashMap<String, String> starsInRashiWithPadam = new LinkedHashMap<String, String>();

		ArrayList<String> starsPadamList = new ArrayList<String>();
		for (Nakshatra nakshatra : Nakshatra.values()) {
			for (int i = 1; i <= 4; i++) {
				starsPadamList.add(nakshatra.name() + "_" + i);
			}
		}

		int current = 0;
		for (Rashi rashi : Rashi.values()) {
			for (int i = 1; i <= 9; i++) {
				starsInRashiWithPadam.put(starsPadamList.get(current++), rashi.name());
			}
		}
		return starsInRashiWithPadam;
	}

}