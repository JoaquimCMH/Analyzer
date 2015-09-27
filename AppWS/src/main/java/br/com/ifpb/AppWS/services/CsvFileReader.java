package br.com.ifpb.AppWS.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import br.com.ifpb.AppWS.model.InQuestion;

public class CsvFileReader {
	private static final String[] FILE_HEADER_MAPPING = {"id", "body", "tagName"};
	private static final String BODYID = "id";
	private static final String BODY = "body";
	private static final String TAGNAME = "tagName";
	private static final String FILE_NAME = "D:/extra/DataSet_StackOverflow.csv";
	
	public CsvFileReader() {
	}

	public List<InQuestion> readCsvFile() {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {
			List<InQuestion> questions = new ArrayList<InQuestion>();
			fileReader = new FileReader(FILE_NAME);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				TreatmentDataBase treatment = new TreatmentDataBase();
				InQuestion question = new InQuestion(record.get(BODYID), treatment.clearRatings(record.get(BODY)), record.get(TAGNAME));
				questions.add(question);
			}
			return questions;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		throw new RuntimeException("Erro o tratar o dataset");
	}
}
