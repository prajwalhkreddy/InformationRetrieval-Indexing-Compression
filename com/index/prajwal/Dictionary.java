package com.index.prajwal;

import java.util.List;

public class Dictionary {

	private String word;
	private int docFrequeny;
	private List<PostingList> pL;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getDocFrequeny() {
		return docFrequeny;
	}

	public void setDocFrequeny(int docFrequeny) {
		this.docFrequeny = docFrequeny;
	}

	public List<PostingList> getpL() {
		return pL;
	}

	public void setpL(List<PostingList> pL) {
		this.pL = pL;
	}


}
