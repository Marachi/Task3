package potaychuk.com.ua.knife;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;
import java.util.List;

/**
 * Created by potaychuk on 04.07.2016.
 */
@XmlRootElement(name = "Knife")
@XmlType(propOrder = {"type", "handy", "origin", "visual" , "value"})
public class Blade {

    private int id;

    private Type type;
    private Handy handy;
    private String origin;
    private List<Visual> visual;
    private boolean value;

    public Blade() {
    }

    public Blade(int id, Type type, Handy handy, String origin, List<Visual> visual, boolean value) {
        this.id = id;
        this.type = type;
        this.handy = handy;
        this.origin = origin;
        this.visual = visual;
        this.value = value;
    }

    @XmlEnum
    public enum Type{
        DAGGER, SWORD, AXE, KNIFE
    }
    @XmlEnum
    public enum Handy{
        ONE_HANDED, TWO_HANDED
    }

    public static class Visual{

        @XmlElement(name = "Length")
        private double length;

        @XmlElement(name = "Width")
        private double width;

        @XmlElement(name = "Material")
        private Material material;

        @XmlElement(name = "Handle")
        private Handle handle;

        @XmlElement(name = "Fuller_exist")
        private boolean fuller;

        @XmlEnum
        public enum Material{
            STEEL, COPPER, SILVER
        }

        @XmlEnum
        public enum Handle{
            WOOD_OAK, WOOD_HORNBEAM, WOOD_PINE,
            PLASTIC,
            STEEL,
        }


        public Visual() {
        }


        public Visual(double length, double width, Material material, Handle handle, boolean fuller) {
            this.length = length;
            this.width = width;
            this.material = material;
            this.handle = handle;
            this.fuller = fuller;
        }

        @Override
        public String toString() {
            return "Visual{" +
                    "length=" + length +
                    ", width=" + width +
                    ", material=" + material +
                    ", handle=" + handle +
                    ", fuller=" + fuller +
                    '}';
        }
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    @XmlElement(name = "Collectible")
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @XmlElement(name = "Type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @XmlElement(name = "Handy")
    public Handy getHandy() {
        return handy;
    }

    public void setHandy(Handy handy) {
        this.handy = handy;
    }

    @XmlElement(name = "Made_in")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @XmlElementWrapper(name = "Visuals")
    public List<Visual> getVisual() {
        return visual;
    }

    public void setVisual(List<Visual> visual) {
        this.visual = visual;
    }

    @Override
    public String toString() {
        return "Blade{" +
                "id=" + id +
                ", type=" + type +
                ", handy=" + handy +
                ", origin='" + origin + '\'' +
                ", visual size=" + visual.toString() +
                ", value=" + value +
                '}';
    }
}
