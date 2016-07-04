package potaychuk.com.ua.knife;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by potaychuk on 04.07.2016.
 */
public class Model {

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
        blade.setType(Blade.Type.DAGGER);
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
