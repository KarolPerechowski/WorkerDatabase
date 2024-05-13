package com.example.handlers;

import com.example.models.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {

    public List<Worker> readWorkersFromXML(String fileName) {
        List<Worker> workers = new ArrayList<>();
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("worker");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String personId = element.getElementsByTagName("personId").item(0).getTextContent();
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String telephoneNumber = element.getElementsByTagName("telephoneNumber").item(0).getTextContent();
                    String email = element.getElementsByTagName("email").item(0).getTextContent();
                    String pesel = element.getElementsByTagName("pesel").item(0).getTextContent();
                    boolean isInternal = Boolean.parseBoolean(element.getElementsByTagName("isInternal").item(0).getTextContent());
                    Worker worker = new Worker(personId, firstName, lastName, telephoneNumber, email, pesel, isInternal);
                    workers.add(worker);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workers;
    }


    public void writeWorkersToXML(List<Worker> workers, String fileName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("workers");
            doc.appendChild(rootElement);

            for (Worker worker : workers) {
                Element workerElement = doc.createElement("worker");
                rootElement.appendChild(workerElement);

                Element personIdElement = doc.createElement("personId");
                personIdElement.appendChild(doc.createTextNode(worker.getPersonId()));
                workerElement.appendChild(personIdElement);

                Element firstNameElement = doc.createElement("firstName");
                firstNameElement.appendChild(doc.createTextNode(worker.getFirstName()));
                workerElement.appendChild(firstNameElement);

                Element lastNameElement = doc.createElement("lastName");
                lastNameElement.appendChild(doc.createTextNode(worker.getLastName()));
                workerElement.appendChild(lastNameElement);

                Element telephoneNumberElement = doc.createElement("telephoneNumber");
                telephoneNumberElement.appendChild(doc.createTextNode(worker.getTelephone()));
                workerElement.appendChild(telephoneNumberElement);

                Element emailElement = doc.createElement("email");
                emailElement.appendChild(doc.createTextNode(worker.getEmail()));
                workerElement.appendChild(emailElement);

                Element peselElement = doc.createElement("pesel");
                peselElement.appendChild(doc.createTextNode(worker.getPesel()));
                workerElement.appendChild(peselElement);

                Element isInternalElement = doc.createElement("isInternal");
                isInternalElement.appendChild(doc.createTextNode(String.valueOf(worker.getInternal())));
                workerElement.appendChild(isInternalElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}