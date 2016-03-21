package com.index.prajwal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.index.prajwal.Dictionary;
import com.index.prajwal.Stemmer;

public class Indexer {

	public static TreeMap<String, Dictionary> storageVersionOne = new TreeMap<String, Dictionary>();
	public static TreeMap<String, Dictionary> storageVersionTwo = new TreeMap<String, Dictionary>();
	public static HashSet<String> wordListPerDocument;
	public static HashMap<String, Integer> termFrequency;
	public static HashMap<String, Integer> documentFrequency1 = new HashMap<String, Integer>();
	public static HashMap<String, Integer> documentFrequency2 = new HashMap<String, Integer>();
	public static List<String> fileNames = new ArrayList<String>();
	public static List<String> stopWords= new ArrayList<String>();
	public static File folder;
	private static int docLength = 0;
	static Dictionary d;
	static PostingList p;
	private static List<PostingList> postList;
	private static List<PostingList> tempList;

	public static void main(String[] args) {
		folder = new File(args[0]);
		listFilesForFolder(folder);
		try {
			readStopWords();
			System.out.println(stopWords);
			createVersionOne();
			createVersionTwo();
			System.out.println("Done");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static TreeMap<String,Dictionary> createVersionOne() throws FileNotFoundException {

		for (int i = 0; i < fileNames.size(); i++) {

			postList = new ArrayList<PostingList>();
			docLength = 0;
			wordListPerDocument = new HashSet<String>();
			termFrequency = new HashMap<String, Integer>();
			String tempPath = folder + "\\" + fileNames.get(i);
			@SuppressWarnings("resource")
			Scanner readFile = new Scanner(new File(tempPath));

			String word;
			while (readFile.hasNextLine()) {

				String curLine = readFile.nextLine();
				if (!(curLine.contains("<") && curLine.contains(">"))) {
					curLine = curLine.replaceAll("[-]", " ");
					StringTokenizer stringTokenizer = new StringTokenizer(
							curLine);

					while (stringTokenizer.hasMoreTokens()) {
						word = stringTokenizer.nextToken()
								.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
						if (word.equals(""))
							continue;
						else if(stopWords.contains(word)){
							docLength+=1;
							continue;
						}
						else {
							docLength += 1;
							wordListPerDocument.add(word);

							if (termFrequency.containsKey(word))
								termFrequency.get(termFrequency.get(word) + 1);
							else
								termFrequency.put(word, 1);

							if (storageVersionOne.containsKey(word)) {
								d = storageVersionOne.get(word);
								postList = d.getpL();
								//System.out.println(postList.size());
								if(postList.contains(i))
								p = postList.get(i);
								else
								p.setId(i);
								p.setTermFrequency(termFrequency.get(word));
								d.setpL(postList);
								storageVersionOne.put(word, d);
							} else {
								d = new Dictionary();
								p = new PostingList();
								d.setWord(word);
								p.setId(i);
								p.setTermFrequency(termFrequency.get(word));
								postList.add(p);
								d.setpL(postList);
								storageVersionOne.put(word, d);
							}

						}
						// d.setDocFrequeny(documentFrequency.get(d.getWord()));
					}
				}

			}
			calculateDocumentFrequency1(wordListPerDocument);
			TreeMap<String, Integer> map = sortTermFrequency(termFrequency);
			Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
			int freqTerm = entry.getValue();
			for (String str : wordListPerDocument) {
				d = storageVersionOne.get(str);
				postList = d.getpL();
				d.setDocFrequeny(documentFrequency1.get(d.getWord()));
				if(postList.contains(i))
				p = postList.get(p.getId());
				p.setFrequentTerm(freqTerm);
				p.setDocLength(docLength);

			}
		}
			return storageVersionOne;
	}

	public static TreeMap<String,Dictionary> createVersionTwo() throws FileNotFoundException {

		for (int i = 0; i < fileNames.size(); i++) {

			postList = new ArrayList<PostingList>();
			docLength = 0;
			wordListPerDocument = new HashSet<String>();
			termFrequency = new HashMap<String, Integer>();
			String tempPath = folder + "\\" + fileNames.get(i);
			@SuppressWarnings("resource")
			Scanner readFile = new Scanner(new File(tempPath));

			String word;
			while (readFile.hasNextLine()) {

				String curLine = readFile.nextLine();
				if (!(curLine.contains("<") && curLine.contains(">"))) {
					curLine = curLine.replaceAll("[-]", " ");
					StringTokenizer stringTokenizer = new StringTokenizer(
							curLine);

					while (stringTokenizer.hasMoreTokens()) {
						word = stringTokenizer.nextToken()
								.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
						
						
						/*Stemming Process*/
	      			    Stemmer myStemmer = new Stemmer();
	      			    myStemmer.add(word.toCharArray(),word.length());
	      			    myStemmer.stem();
	      			    word=myStemmer.toString();
	      			    
						if (word.equals(""))
							continue;
						else if(stopWords.contains(word)){
							docLength+=1;
							continue;
						}
							
						else {
							docLength += 1;
							wordListPerDocument.add(word);

							if (termFrequency.containsKey(word))
								termFrequency.get(termFrequency.get(word) + 1);
							else
								termFrequency.put(word, 1);

							if (storageVersionOne.containsKey(word)) {
								d = storageVersionOne.get(word);
								postList = d.getpL();
								//System.out.println(postList.size());
								if(postList.contains(i))
								p = postList.get(i);
								else
								p.setId(i);
								p.setTermFrequency(termFrequency.get(word));
								d.setpL(postList);
								storageVersionOne.put(word, d);
							} else {
								d = new Dictionary();
								p = new PostingList();
								d.setWord(word);
								p.setId(i);
								p.setTermFrequency(termFrequency.get(word));
								postList.add(p);
								d.setpL(postList);
								storageVersionOne.put(word, d);
							}

						}
						// d.setDocFrequeny(documentFrequency.get(d.getWord()));
					}
				}

			}
			calculateDocumentFrequency2(wordListPerDocument);
			TreeMap<String, Integer> map = sortTermFrequency(termFrequency);
			Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
			int freqTerm = entry.getValue();
			for (String str : wordListPerDocument) {
				d = storageVersionOne.get(str);
				postList = d.getpL();
				d.setDocFrequeny(documentFrequency2.get(d.getWord()));
				if(postList.contains(i))
				p = postList.get(p.getId());
				p.setFrequentTerm(freqTerm);
				p.setDocLength(docLength);

			}
		}
				return storageVersionTwo;
	}
	

	public static void calculateDocumentFrequency1(HashSet<String> temp) {

		for (String s : temp) {
			if (documentFrequency1.containsKey(s)) {
				documentFrequency1.put(s, documentFrequency1.get(s) + 1);
			} else {
				documentFrequency1.put(s, 1);
			}
		}
	}
	
	public static void calculateDocumentFrequency2(HashSet<String> temp) {

		for (String s : temp) {
			if (documentFrequency2.containsKey(s)) {
				documentFrequency2.put(s, documentFrequency2.get(s) + 1);
			} else {
				documentFrequency2.put(s, 1);
			}
		}
	}


	private static TreeMap<String, Integer> sortTermFrequency(
			Map<String, Integer> tempMap) {
		class My_Comapartor implements Comparator<String> {

			Map<String, Integer> map;

			public My_Comapartor(Map<String, Integer> base) {
				this.map = base;
			}

			public int compare(String firstvalue, String secondvalue) {

				if (map.get(firstvalue) >= map.get(secondvalue)) {
					return -1;
				} else {
					return 1;
				}
			}
		}

		My_Comapartor vc = new My_Comapartor(tempMap);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(vc);
		sortedMap.putAll(tempMap);
		return sortedMap;
	}

	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				fileNames.add(fileEntry.getName().toString());
			}
		}
	}
	
	public static void readStopWords(){
		String path="StopWords";
		try {
			Scanner readFile = new Scanner(new File(path));
			while(readFile.hasNext()){
				stopWords.add(readFile.next());
			}
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
		
	}
}
