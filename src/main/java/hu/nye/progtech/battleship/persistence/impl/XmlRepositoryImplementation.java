package hu.nye.progtech.battleship.persistence.impl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.service.Game;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML Writer class.
 */
public class XmlRepositoryImplementation {

    private PrintWrapper printWrapper;
    private Player[] players;

    public XmlRepositoryImplementation(Player[] players, PrintWrapper printWrapper) {
        this.players = players;
        this.printWrapper = printWrapper;
    }

    public XmlRepositoryImplementation() {
    }

    /**
     * Saves a status of players in the game to an XML file.
     */
    public void saveToXML() {
        Document xmlDocument;
        Element xmlElement;
        DocumentBuilderFactory documentBuilder = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = documentBuilder.newDocumentBuilder();
            xmlDocument = db.newDocument();

            Element rootElement = xmlDocument.createElement("players");
            for (Player player : this.players) {
                Element xmlPlayerElement = xmlDocument.createElement("player");
                xmlElement = xmlDocument.createElement("name");
                xmlElement.appendChild(xmlDocument.createTextNode(player.getName()));
                xmlPlayerElement.appendChild(xmlElement);

                xmlElement = xmlDocument.createElement("winning");
                xmlElement.appendChild(xmlDocument.createTextNode((player.getTotalLivesLeft() > 0 ? "yes" : "no")));
                xmlPlayerElement.appendChild(xmlElement);

                rootElement.appendChild(xmlPlayerElement);
            }

            xmlDocument.appendChild(rootElement);

            try {
                Transformer transformator = TransformerFactory.newInstance().newTransformer();
                transformator.setOutputProperty(OutputKeys.INDENT, "yes");
                transformator.setOutputProperty(OutputKeys.METHOD, "xml");
                transformator.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformator.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                transformator.transform(new DOMSource(xmlDocument),
                        new StreamResult(new FileOutputStream("game_save.xml")));

            } catch (TransformerException | IOException ex) {
                printWrapper.printLine(ex.getMessage());
            }
        } catch (ParserConfigurationException ex) {
            printWrapper.printLine("Hiba a feldolgozás közben. " + ex);
        }
    }

}