/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Gudni
 */
public class FileHandler {
    
    private String path;
    private int numberOfLines;

    /**
     *
     * @param filePath Stien til den fil som skal bruges
     */
    public FileHandler(String filePath) {
        path = filePath;
    }

    /**
     * Metoden bruges til at læse fra en fil.
     *
     * @return Fildataen
     * @throws IOException
     */
    public ArrayList<String> openFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        int numberOfLines = readLines();
        ArrayList<String> textData = new ArrayList();
        for (int i = 0; i < numberOfLines; i++) {
            textData.add(textReader.readLine());
        }
        textReader.close();
        ArrayList<String> sorted = new ArrayList<>();
        for (String td : textData) {
            if (!td.startsWith("//")) {
                sorted.add(td);
            }
        }
        return sorted;
    }

    /**
     * Metode som bruges til at åbne film og læsse antallet af linjer.
     *
     * @return Antallet af linjer i filen.
     * @throws IOException
     */
    public int readLines() throws IOException {
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

    /**
     * Metode til at opdatere den valgte fil sådan at den indeholder den nye DB
     * information. Metoden bruges til at skrive database indstillingerne ned i
     * en fil.
     *
     * @param usr Database Brugernavn
     * @param pwd Database Kodeord
     * @param url Database URL
     * @param schema Database navn
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void updateDBFile(String usr, String pwd, String url, String schema) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Databaseindstillinger.txt");
        writer.println("//USERNAME");
        writer.println(usr);
        writer.println("//PASSWORD");
        writer.println(pwd);
        writer.println("//URL");
        writer.println(url);
        writer.println("//SCHEMA");
        writer.println(schema);
        writer.close();
    }
    
}
