/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

import java.io.IOException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoJSONcontroller {

    public void transformXMLintoJSON(String jsonFile) throws IOException {
        XMLtoJSONTransformer transform = new XMLtoJSONTransformer();
        transform.XMLtoJSON(jsonFile);
    }
}
