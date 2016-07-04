package potaychuk.com.ua;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "age",
        "type"
})
@XmlRootElement(name = "Blade")
/**
 * Created by potaychuk on 04.07.2016.
 */
public class TestXML {

//    @XmlElementWrapper
//    @XmlElement
//    private List<TesxtXML2> list;

    @XmlEnum
    private enum Type{
        SWORD, AXE
    }


    @XmlElement(required = true)
    private String name = "Duke";

    @XmlElement(required = true)
    private int age = 13;

    @XmlElement(required = true)
    private Type type = Type.AXE;

//    @XmlElement(required = true)
    void doSomething(int a, String s){
        System.out.println(a);
        System.out.println(s);
    }
}
