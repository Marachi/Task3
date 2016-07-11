package mvc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import knife.Blade;
import knife.Blade.Type;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by potaychuk on 04.07.2016.
 */
public class Model {


    public Blade parseWithStAX(File file) throws FileNotFoundException, XMLStreamException {
        Blade blade = new Blade();
        String tagContent ="";
        int countVisual=0;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(file));
        while (reader.hasNext()){
            int event = reader.next();
            switch (event){
                case XMLStreamConstants.START_ELEMENT:
                    if (reader.getLocalName().equals(View.BLADE_KNIFE)){
                        blade.setId(Integer.valueOf(reader.getAttributeValue(0)));
                    }
                    if (reader.getLocalName().equals(View.BLADE_VISUAL_LIST)){
                        blade.setVisual(new LinkedList<>());
                    } if (reader.getLocalName().equals(View.BLADE_VISUAL)){
                        blade.getVisual().add(new Blade.Visual());
                    }

                    break;
                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()){
                        case View.BLADE_TYPE:
                            blade.setType(Type.valueOf(tagContent));
                            break;
                        case View.BLADE_HANDY:
                            blade.setHandy(Blade.Handy.valueOf(tagContent));
                            break;
                        case View.BLADE_ORIGIN:
                            blade.setOrigin(tagContent);
                            break;
                        case View.BLADE_VALUE:
                            blade.setValue(Boolean.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL_LENGTH:
                            blade.getVisual().get(countVisual).setLength(Double.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL_WIDTH:
                            blade.getVisual().get(countVisual).setWidth(Double.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL_MATERIAL:
                            blade.getVisual().get(countVisual).setMaterial(Blade.Visual.Material.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL_HANDLE:
                            blade.getVisual().get(countVisual).setHandle(Blade.Visual.Handle.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL_FULLER:
                            blade.getVisual().get(countVisual).setFuller(Boolean.valueOf(tagContent));
                            break;
                        case View.BLADE_VISUAL:
                            countVisual++;
                            break;
                    }
                    break;
            }
        }

        return blade;
    }

    public Blade parseWithSAX(File file) throws ParserConfigurationException, SAXException, IOException {
        Blade blade = new Blade();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = saxParserFactory.newSAXParser();
        parser.parse(file,new DefaultHandler(){
            private int visualCount;
            String thisElement="";
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                if (qName.equals(View.BLADE_KNIFE)) {
                    blade.setId(Integer.valueOf(attributes.getValue(0)));
                }
                thisElement=qName;

            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equals(View.BLADE_VISUAL)){
                    visualCount++;
                }
                thisElement="";
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String temp=new String(ch, start, length);
                switch (thisElement) {
                    case View.BLADE_TYPE:
                        blade.setType(Blade.Type.valueOf(temp));
                        break;
                    case View.BLADE_HANDY:
                        blade.setHandy(Blade.Handy.valueOf(temp));
                        break;
                    case View.BLADE_ORIGIN:
                        blade.setOrigin(temp);
                        break;
                    case View.BLADE_VALUE:
                        blade.setValue(Boolean.valueOf(temp));
                        break;
                    case View.BLADE_VISUAL_LIST:
                        blade.setVisual(new LinkedList<>());
                        break;
                    case View.BLADE_VISUAL:
                        blade.getVisual().add(visualCount, new Blade.Visual());
                        break;
                    case View.BLADE_VISUAL_LENGTH:
                        blade.getVisual().get(visualCount).setLength(Double.valueOf(temp));
                        break;
                    case View.BLADE_VISUAL_WIDTH:
                        blade.getVisual().get(visualCount).setWidth(Double.valueOf(temp));
                        break;
                    case View.BLADE_VISUAL_MATERIAL:
                        blade.getVisual().get(visualCount).setMaterial(Blade.Visual.Material.valueOf(temp));
                        break;
                    case View.BLADE_VISUAL_HANDLE:
                        blade.getVisual().get(visualCount).setHandle(Blade.Visual.Handle.valueOf(temp));
                        break;
                    case View.BLADE_VISUAL_FULLER:
                        blade.getVisual().get(visualCount).setFuller(Boolean.valueOf(temp));
                        break;
                }
            }
        });
        return blade;
    }
    public Blade parseWithDOM(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
        Document document = db.parse(file);
        return createBladeFromDoc(document);
    }


    private  Blade createBladeFromDoc(Document document){
        Blade blade = new Blade();

        blade.setType(Blade.Type.valueOf(document.getElementsByTagName(View.BLADE_TYPE).item(0).getTextContent()));
        blade.setHandy(Blade.Handy.valueOf(document.getElementsByTagName(View.BLADE_HANDY).item(0).getTextContent()));
        blade.setOrigin(document.getElementsByTagName(View.BLADE_ORIGIN).item(0).getTextContent());
        blade.setValue(Boolean.valueOf(document.getElementsByTagName(View.BLADE_VALUE).item(0).getTextContent()));
        blade.setId(Integer.valueOf(document.getDocumentElement().getAttribute(View.BLADE_ID)));

        NodeList listVisuals = document.getElementsByTagName(View.BLADE_VISUAL_LIST).item(0).getChildNodes();

        blade.setVisual(new LinkedList<>());

        for(int i = 0; i<listVisuals.getLength(); i++){
            if(listVisuals.item(i).getNodeName().equals(View.BLADE_VISUAL)){
                Blade.Visual blade1Visual = new Blade.Visual();
                blade1Visual.setLength(Double.valueOf(((Element)(listVisuals.item(i))).getElementsByTagName(View.BLADE_VISUAL_LENGTH).item(0).getTextContent()));
                blade1Visual.setWidth(Double.valueOf(((Element)(listVisuals.item(i))).getElementsByTagName(View.BLADE_VISUAL_WIDTH).item(0).getTextContent()));
                blade1Visual.setMaterial(Blade.Visual.Material.valueOf(((Element)(listVisuals.item(i))).getElementsByTagName(View.BLADE_VISUAL_MATERIAL).item(0).getTextContent()));
                blade1Visual.setHandle(Blade.Visual.Handle.valueOf(((Element)(listVisuals.item(i))).getElementsByTagName(View.BLADE_VISUAL_HANDLE).item(0).getTextContent()));
                blade1Visual.setFuller(Boolean.valueOf(((Element)(listVisuals.item(i))).getElementsByTagName(View.BLADE_VISUAL_FULLER).item(0).getTextContent()));
                blade.getVisual().add(blade1Visual);
            }
        }

        return  blade;
    }

    public static void visit(Node node, int level) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i); // текущий нод

//            System.out.println("childNode.getNodeName(): "+childNode.getNodeName());
//            System.out.println("childNode.getTextContent(): "+childNode.getTextContent());
            process(childNode, level + 1); // обработка
            visit(childNode, level + 1); // рекурсия
        }
    }
    public static void process(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print('\t');
        }
        System.out.println("node.getNodeName(): "+ node.getNodeName());
        if (node instanceof Element){
            Element e = (Element) node;
//            System.out.println("e.getTextContent(): " +e.getTextContent());

        }
        System.out.println();
    }



    public void fromObjToXml(Object obj, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal( obj, file);
    }

    public Object fromXmlToObj(Object obj, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    public Blade bladeFactory(){
        Blade blade = new Blade();
        blade.setId(1);
        blade.setValue(true);
        blade.setType(Type.DAGGER);
        blade.setHandy(Blade.Handy.ONE_HANDED);
        blade.setOrigin("UK");
        blade.setVisual(new LinkedList<>());
        blade.getVisual().add(new Blade.Visual(200, 19 , Blade.Visual.Material.STEEL,  Blade.Visual.Handle.STEEL, true));
        blade.getVisual().add(new Blade.Visual(400, 15 , Blade.Visual.Material.SILVER,  Blade.Visual.Handle.WOOD_OAK, false));
        blade.getVisual().add(new Blade.Visual(100, 15 , Blade.Visual.Material.COPPER,  Blade.Visual.Handle.WOOD_PINE, false));
        blade.getVisual().add(new Blade.Visual(700, 29 , Blade.Visual.Material.COPPER,  Blade.Visual.Handle.WOOD_OAK, true));
        blade.getVisual().add(new Blade.Visual(500, 12 , Blade.Visual.Material.SILVER,  Blade.Visual.Handle.PLASTIC, false));
        return blade;
    }


}
