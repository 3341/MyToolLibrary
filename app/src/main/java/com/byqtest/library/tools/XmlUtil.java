package com.byqtest.library.tools;

import java.io.File;
import org.w3c.dom.Document;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.FileInputStream;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.List;
import org.w3c.dom.NodeList;

public class XmlUtil {
    private File file;
    private Document document;
    private Element rootElement;

    private XmlUtil(File file) {
        this.file = file;
        rootElement = document.getDocumentElement();
    }

    public String itNodes() {
        StringBuilder sb = new StringBuilder();
        sb.append(rootElement.getNodeName());
        return itNodes(rootElement.getNextSibling().getChildNodes(),sb,rootElement.getNodeName());
    }

    private String itNodes(NodeList nodes,StringBuilder sb,String currPath) {
        for(int i=0;i<nodes.getLength();i++) {
            Node node = nodes.item(i);
            sb.append("\n"+currPath+">"+node.getNodeName());
            itNodes(node.getChildNodes(),sb,currPath+">"+node.getNodeName());
        }
        return sb.toString();
    }

    public static Document create() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            return document;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    public static Document loadWithDom(String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                throw new RuntimeException("not find file:" + xmlFilePath);
            } 
            else {
                //InputStream inputStream = new FileInputStream(file);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);
                /*try {
                 inputStream.close();
                 } catch (Exception e) {
                 e.printStackTrace();
                 }*/
                return document;
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static XmlUtil parse(File f) {
        XmlUtil xml = new XmlUtil(f);
        xml.document = loadWithDom(f.getPath());
        return xml;
    }
}


