package com.example.xomel;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        TextView content = findViewById(R.id.xmlContent);
        String file = getIntent().getStringExtra("transfer");

        if (file == null || file.isEmpty()) {
            Log.e("XmlTemplate", "No XML file was specified in the Intent");
            content.setText("Nie wybrano pliku XML");
            return;
        }

        setTitle(file);
        content.setText(getXmlFileContent(file));
    }

    private String getXmlFileContent(String file)
    {
        StringBuilder output = new StringBuilder("DANE:");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try (InputStream stream = getAssets().open(file)) {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document =  dBuilder.parse(stream);
            NodeList node = document.getElementsByTagName("towar");
            for (int i = 0; i < node.getLength(); i++)
            {
                Node tasking = node.item(i);
                if (tasking.getNodeType() == Node.ELEMENT_NODE){
                    Element task = (Element) tasking;
                    String id = task.getAttribute("id");
                    output.append("\n    Zlecenie nr ")
                            .append(id)
                            .append(":");
                    NodeList registry = task.getChildNodes();
                    for (int j = 0; j < registry.getLength(); j++){
                        Node detailing = registry.item(j);
                        if (detailing.getNodeType() == Node.ELEMENT_NODE){
                            Element detail = (Element) detailing;
                            output.append("\n        ")
                                    .append(detail.getTagName())
                                    .append(": ")
                                    .append(detail.getTextContent());
                        }
                    }
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            Log.e("XmlTemplate", "Nie odczytano pliku XML:" + file, e);
            return "Nie odczytano pliku XML.";
        }

        return output.toString();
    }



}
