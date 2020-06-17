/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.domain;

import eapli.base.productmanagement.application.RegisterProductController;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Tiago Ribeiro
 */
public class ReaderManager {

    private final RegisterProductController controller = new RegisterProductController();

    public void importProducts(String fileName) {
        try {
            Scanner reader = new Scanner(new File(fileName));
            reader.nextLine();
            reader.useDelimiter(";|\n");

            try {
                while (reader.hasNext()) {
                    String code = reader.next().trim();
                    String commercialCode = reader.next().trim();
                    String briefDesc = reader.next().trim();
                    String fullDesc = reader.next().trim();
                    String measureUnit = reader.next().trim();
                    String cat = reader.next().trim();

                    controller.registerProductWithoutBOM(code, commercialCode, briefDesc, fullDesc, cat, measureUnit);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reader the file. Please check the corresponding.csv file.");
                e.printStackTrace();
                exit(0);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The specified file " + fileName + " doesn't exist.");
            e.printStackTrace();
            exit(0);
        }
    }
}
