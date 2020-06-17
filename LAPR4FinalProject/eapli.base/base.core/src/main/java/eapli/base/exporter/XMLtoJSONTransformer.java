/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import org.json.XML;
import org.json.JSONObject;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoJSONTransformer {

    private static final String XML_FILE = "factoryInfo.xml";
    private static final int INDENT_FACTOR = 4;

    public void XMLtoJSON(String jsonFile) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(XML_FILE));
        String content = br.lines().collect(Collectors.joining("\n"));

        convertXMLToJSON(content, jsonFile);
    }

    public static void convertXMLToJSON(String content, String jsonFileName) throws IOException {

        JSONObject jsonObject = XML.toJSONObject(content);
        String jsonPrettyPrintString = jsonObject.toString(INDENT_FACTOR);

        FileWriter fw = new FileWriter(jsonFileName);
        fw.write(jsonPrettyPrintString);
        fw.close();
    }
}
