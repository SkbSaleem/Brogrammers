package edu.unsw.comp9321;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderClass {
	private List wordsList;
	public FileReaderClass() {}
	public FileReaderClass(String filename) {
		String line = null;
	        try {
				ClassLoader classLoader = getClass().getClassLoader();

				File file = new File(classLoader.getResource(filename).toURI());
				wordsList = new ArrayList();
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(file);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	                //System.out.println("word: " + line);	            
	                wordsList.add(line.toLowerCase());
	            }   

	            // Always close files.
	            bufferedReader.close();     
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" );                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        } catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List getWordsList() {
		return wordsList;
	}

	public void setAbuseWordsList(List list) {
		this.wordsList = list;
	}
}
