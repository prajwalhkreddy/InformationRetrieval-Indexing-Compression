package com.index.prajwal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BuildCompressor {

	static Dictionary d;
	static PostingList p;
	static CompressedDictionary cd;
	static CompressedPostingList cp;
	static Encoding en = new Encoding();
	private static List<PostingList> unCompressedPostingList;
	private static List<CompressedPostingList> compressedPostingList;
	private static TreeMap<String, CompressedDictionary> compressedVersion = new TreeMap<String, CompressedDictionary>();

	public static TreeMap<String, CompressedDictionary> buildCompressedIndex(
			Map<String, Dictionary> unCompressedIndex, int version) {

		for (String term : unCompressedIndex.keySet()) {
			d = unCompressedIndex.get(term);
			int prevDocID = 0;
			for (PostingList pL : d.getpL()) {
				compressedPostingList = new LinkedList<CompressedPostingList>();

				int gap = pL.getId() - prevDocID;
				int tf = pL.getTermFrequency();
				int freqTerm = pL.getFrequentTerm();
				int docLength = pL.getDocLength();

				prevDocID = pL.getId();
				byte[] gapByte = null;
				byte[] termFreqByte = null;
				byte[] frequentTermByte= null;
				byte[] docLengthByte=null;
				
				if (version == 1) {

					gapByte = Encoding.calculateDeltaByte(gap);
					termFreqByte = Encoding.calculateDeltaByte(tf);
					frequentTermByte = Encoding.calculateDeltaByte(freqTerm);
					docLengthByte = Encoding.calculateDeltaByte(docLength);
				} else if (version == 2) {
					gapByte = Encoding.calculateGammaByte(gap);
					termFreqByte = Encoding.calculateGammaByte(tf);
					frequentTermByte = Encoding.calculateGammaByte(freqTerm);
					docLengthByte = Encoding.calculateGammaByte(docLength);
				}
				CompressedPostingList cPL = new CompressedPostingList();
				cPL.setId(gapByte);
				cPL.setTermFrequency(termFreqByte);
				cPL.setFrequentTerm(frequentTermByte);
				cPL.setDocLength(docLengthByte);
				compressedPostingList.add(cPL);
			}
			byte[] docFrequencyByte=null;
			if (version == 1) {
				docFrequencyByte = Encoding.calculateDeltaByte(d.getDocFrequeny());
			} else if (version == 2) {
				docFrequencyByte = Encoding.calculateGammaByte(d.getDocFrequeny());
			}
			cd = new CompressedDictionary();
			cd.setDocFrequeny(docFrequencyByte);
			cd.setWord(d.getWord());
			cd.setpL(compressedPostingList);

			compressedVersion.put(d.getWord(), cd);
		}

		return compressedVersion;
	}
}
