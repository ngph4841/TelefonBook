package telefonbuch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelefonBook {

	private final List<TelefonNumber> list;
	private final ObservableList<TelefonNumber> tNs;
	private final Path filePath;
	
	private TelefonBook(List<TelefonNumber> items, Path p) {
		this.list = new ArrayList<>();
		this.list.addAll(items);
		this.tNs = FXCollections.observableList(list);
		this.filePath = p;
	}

	public void add(TelefonNumber number){
		list.add(number);
	}

	public TelefonNumber get(TelefonNumber number) {
		for(TelefonNumber a : tNs){
			if(a.equals(number)){
				return a;
			}
		}
		return  new TelefonNumber("lastName", "firstName","number");
	}
	
	public ObservableList<TelefonNumber> getObservableList(){
		return tNs;
	}

	public void save() {
	    JsonFactory factory = new JsonFactory();
	    try (OutputStream os = Files.newOutputStream(filePath);
	         JsonGenerator jg = factory.createGenerator(os)) {

	        jg.writeStartObject();
	        jg.writeArrayFieldStart("data");
	        for (TelefonNumber a : tNs) {
	            jg.writeStartObject();
	            jg.writeStringField("name", a.getLastName());
	            jg.writeStringField("surname", a.getFirstName());
	            jg.writeStringField("number", a.getNumber());
	            jg.writeEndObject();
	        }
	        jg.writeEndArray();
	        jg.writeEndObject();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public static TelefonBook loadFromFile(Path p) {
	    List<TelefonNumber> items = new ArrayList<>();
	
	    try (InputStream is = Files.newInputStream(p)) {
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(is);
	        JsonNode entries = root.get("data");
	        for (JsonNode e : entries) {
	            items.add(new TelefonNumber(e.get("name").asText(),
	                    e.get("surname").asText(),
	                    e.get("number").asText()));
	
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
    return new TelefonBook(items, p);
	}
	
}
