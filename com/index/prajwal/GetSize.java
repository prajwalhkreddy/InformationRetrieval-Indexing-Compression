package com.index.prajwal;

import java.util.TreeMap;

public class GetSize {
	public long getUncompressedsize(TreeMap<String, Dictionary> unCompressedMap) {
		long size = 0;
		for (String word : unCompressedMap.keySet()) {
			Dictionary d = unCompressedMap.get(word);
			size = size + 8 + d.getWord().getBytes().length + Integer.SIZE * 2
					+ d.getpL().size() * Integer.SIZE * 2;

		}
		return size;
	}

	public long getCompressedsize(TreeMap<String, CompressedDictionary> compressedMap) {
		long size = 0;
		for (String word : compressedMap.keySet()) {
			CompressedDictionary cd = compressedMap.get(word);
			size = size + 8 + cd.getWord().length()
					+ cd.getDocFrequeny().length;
			for (CompressedPostingList cpl : cd.getpL()) {
				size = size + cpl.getId().length
						+ cpl.getTermFrequency().length
						+ cpl.getFrequentTerm().length
						+ cpl.getDocLength().length;
			}
		}
		return size;
	}
}
