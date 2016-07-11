package mvc;


import org.xml.sax.SAXException;
import knife.Blade;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Controller {

    /**
     *Model
     */
    Model model;

    /**
     * View
     */
    View view;

    /**
     * Constructor
     * @param model is model
     * @param view is view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * This method create blade object and marshals it to xml file
     * Than blade object is unmarshalling from xml with 4 API
     * 1) JAXB
     * 2) DOM
     * 3) SAX
     * 4) StAX
     */
    public void processUSer() {


        File originalBlade = new File(View.ORIGINAL_BLADE_XML_FILE);  //create File
        Blade blade = model.bladeFactory();                           //create blade using model's factory method
        view.printMsg(View.ORIGINAL_BLADE+blade);                     //view blade object

        try {
            /*create xml file and fill in it blade*/
            File bladeXMLFile = new File(View.BLADE_XML_FILE);
            model.fromObjToXml(blade,bladeXMLFile);
            /*view created blade from xml file with JAXB*/
            view.printMsg(View.JAXB_BLADE+model.fromXmlToObj(new Blade(),originalBlade));
            /*view created blade from xml file with DOM*/
            view.printMsg(View.DOM_BLADE+model.parseWithDOM(originalBlade));
            /*view created blade from xml file with SAX*/
            view.printMsg(View.SAX_BLADE+model.parseWithSAX(originalBlade));
            /*view created blade from xml file with StAX*/
            view.printMsg(View.STAX_BLADE +model.parseWithStAX(originalBlade));
        } catch (JAXBException | IOException | ParserConfigurationException | SAXException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
