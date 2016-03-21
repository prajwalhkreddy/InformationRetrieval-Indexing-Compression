package com.index.prajwal;

public class PostingList {

	private int id;
	private int termFrequency;
	private int frequentTerm;
	private int docLength;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTermFrequency() {
		return termFrequency;
	}

	public void setTermFrequency(int termFrequency) {
		this.termFrequency = termFrequency;
	}

	public int getFrequentTerm() {
		return frequentTerm;
	}

	public void setFrequentTerm(int frequentTerm) {
		this.frequentTerm = frequentTerm;
	}

	public int getDocLength() {
		return docLength;
	}

	public void setDocLength(int docLength) {
		this.docLength = docLength;
	}

}
