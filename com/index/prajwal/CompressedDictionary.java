package com.index.prajwal;

import java.util.List;

public class CompressedDictionary {

	private String word;
	private byte[] docFrequeny;
	private List<CompressedPostingList> pL;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public byte[] getDocFrequeny() {
		return docFrequeny;
	}

	public void setDocFrequeny(byte[] docFrequeny) {
		this.docFrequeny = docFrequeny;
	}

	public List<CompressedPostingList> getpL() {
		return pL;
	}

	public void setpL(List<CompressedPostingList> pL) {
		this.pL = pL;
	}

}
