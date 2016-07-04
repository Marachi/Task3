package potaychuk.com.ua.knife;


import com.sun.xml.internal.bind.v2.runtime.MarshallerImpl;
import com.sun.xml.internal.ws.util.Pool;
import potaychuk.com.ua.knife.Blade;
import potaychuk.com.ua.knife.Model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static potaychuk.com.ua.knife.Blade.*;

public class Controller {




    public void processUSer() {
        Model model = new Model();
        File bladeXMLFile = new File("blade.xml");

        Blade blade = model.bladeFactory();

        System.out.println(blade);
        try {
            model.fromObjToXml(blade,bladeXMLFile);
            System.out.println("=============================");
            System.out.println(model.fromXmlToObj(blade,bladeXMLFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}
