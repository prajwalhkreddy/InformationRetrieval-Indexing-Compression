package com.index.prajwal;

public class CompressedPostingList {

	private byte[] id;
	private byte[] termFrequency;
	private byte[] frequentTerm;
	private byte[] docLength;

	public byte[] getId() {
		return id;
	}
	public void setId(byte[] id) {
		this.id = id;
	}
	public byte[] getTermFrequency() {
		return termFrequency;
	}
	public void setTermFrequency(byte[] termFrequency) {
		this.termFrequency = termFrequency;
	}
	public byte[] getFrequentTerm() {
		return frequentTerm;
	}
	public void setFrequentTerm(byte[] frequentTerm) {
		this.frequentTerm = frequentTerm;
	}
	public byte[] getDocLength() {
		return docLength;
	}
	public void setDocLength(byte[] docLength) {
		this.docLength = docLength;
	}
	
	

}
