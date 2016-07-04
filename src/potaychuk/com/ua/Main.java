package potaychuk.com.ua;


import com.sun.xml.internal.bind.v2.runtime.MarshallerImpl;
import com.sun.xml.internal.ws.util.Pool;
import potaychuk.com.ua.knife.Blade;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static potaychuk.com.ua.knife.Blade.*;

public class Main {

    public static void main(String[] args) throws JAXBException {
	// write your code here

        File xml = new File("2.xml");
        Blade blade = bladeFactory();

        System.out.println(blade);
        fromObjToXml(blade,xml);

        System.out.println("=============================");
        System.out.println(fromXmlToObj(blade,xml));

    }

    static void fromObjToXml(Object obj, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal( obj, file);
    }

    static Object fromXmlToObj(Object obj, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    static Blade bladeFactory(){
        Blade blade = new Blade();
        blade.setId(1);
        blade.setValue(true);
        blade.setType(Type.DAGGER);
        blade.setHandy(Handy.ONE_HANDED);
        blade.setLength(300);
        blade.setWidth(10);
        blade.setMaterial(Material.STEEL);
        return blade;
    }
}
