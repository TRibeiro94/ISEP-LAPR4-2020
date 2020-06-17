/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.ReaderManager;

/**
 * @author Tiago Ribeiro
 */
public class ImportController {


    ReaderManager rm = new ReaderManager();

    public void importer(String fileName) {
        rm.importProducts(fileName);
    }
}
