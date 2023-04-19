package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.gabys.ps_tema2.model.PropertiesListObject;
import com.gabys.ps_tema2.model.Property;
import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExportPropertiesViewModel extends Observable implements IViewModel{

    Context context;
    private ArrayList<Property> propertiesList;

    public ExportPropertiesViewModel(Context context) {
        this.context = context;
        Gson gson = new Gson();
        PropertiesListObject propertiesListObject = gson.fromJson(((Activity)context).getIntent().getStringExtra("propertiesList"), PropertiesListObject.class);
        propertiesList = propertiesListObject.getPropertiesList();
    }

    @Override
    public void refresh() {

    }

    public void onCSVButtonClick (View v){
        LocalDateTime now = LocalDateTime.now();
        File path = v.getContext().getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".csv");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("ID,Titlu,Locatie,Nr Camere,Tip,Pret,Disponibilitate");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Property p : propertiesList){
            try {
                bw.write(p.getId() + "," +
                        p.getTitle() + "," +
                        p.getLocation() + "," +
                        p.getRoomsNo() + "," +
                        p.getType() + "," +
                        p.getPrice() + "," +
                        p.isAvailableToString());
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toast.makeText(v.getContext(), "CSV exportat cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void onJSONButtonClick (View v){
        LocalDateTime now = LocalDateTime.now();
        File path = v.getContext().getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".json");

        String json = new Gson().toJson(propertiesList);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toast.makeText(v.getContext(), "JSON exportat cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void onXMLButtonClick (View v){
        LocalDateTime now = LocalDateTime.now();
        File path = v.getContext().getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".xml");

        try {

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Proprietati");
            doc.appendChild(root);

            for (Property p : propertiesList) {

                Element name = doc.createElement("ID");
                name.appendChild(doc.createTextNode(String.valueOf(p.getId())));
                root.appendChild(name);

                Element title = doc.createElement("Titlu");
                title.appendChild(doc.createTextNode(p.getTitle()));
                root.appendChild(title);

                Element location = doc.createElement("Locatie");
                location.appendChild(doc.createTextNode(p.getLocation()));
                root.appendChild(location);

                Element roomsNo = doc.createElement("Nr_Camere");
                roomsNo.appendChild(doc.createTextNode(String.valueOf(p.getRoomsNo())));
                root.appendChild(roomsNo);

                Element type = doc.createElement("Tip");
                type.appendChild(doc.createTextNode(p.getType()));
                root.appendChild(type);

                Element price = doc.createElement("Pret");
                price.appendChild(doc.createTextNode(String.valueOf(p.getPrice())));
                root.appendChild(price);

                Element available = doc.createElement("Disponibilitate");
                available.appendChild(doc.createTextNode(p.isAvailableToString()));
                root.appendChild(available);
            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter(file);
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }

        Toast.makeText(v.getContext(), "XML exportat cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void onTXTButtonClick (View v){
        LocalDateTime now = LocalDateTime.now();
        File path = v.getContext().getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("Proprietati disponibile:");
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Property p : propertiesList){
            try {
                bw.write((propertiesList.indexOf(p) + 1) + ")");
                bw.newLine();
                bw.write("    ID: " + p.getId());
                bw.newLine();
                bw.write("    Titlu: " + p.getTitle());
                bw.newLine();
                bw.write("    Locatie: " + p.getLocation());
                bw.newLine();
                bw.write("    Nr Camere: " + p.getRoomsNo());
                bw.newLine();
                bw.write("    Tip: " + p.getType());
                bw.newLine();
                bw.write("    Pret: " + p.getPrice());
                bw.newLine();
                bw.write("    Disponibilitate: " + p.isAvailableToString());
                bw.newLine();
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toast.makeText(v.getContext(), "TXT exportat cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void onBackButtonClick (View v){
        ((Activity)context).finish();
    }
}
