package potaychuk.com.ua.knife;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by potaychuk on 04.07.2016.
 */
@XmlRootElement(name = "Knife")
@XmlType(propOrder = {"value", "type", "handy" , "length", "width", "material"})
public class Blade {

    private int id;
    private boolean value;
    private Type type;
    private Handy handy;
    private double length;
    private double width;
    private Material material;


    @XmlEnum
    public enum Type{
        DAGGER, SWORD, AXE, KNIFE
    }

    @XmlEnum
    public enum Handy{
        ONE_HANDED, TWO_HANDED
    }

    @XmlEnum
    public enum Material{
        STEEL, COPPER, SILVER
    }

    public Blade() {
    }

    public Blade(int id, boolean value, Type type, Handy handy, double length, double width, Material material) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.handy = handy;
        this.length = length;
        this.width = width;
        this.material = material;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }



    @XmlElement(name = "collectible")
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @XmlElement
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @XmlElement
    public Handy getHandy() {
        return handy;
    }

    public void setHandy(Handy handy) {
        this.handy = handy;
    }

    @XmlElement
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @XmlElement
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @XmlElement
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Blade{" +
                "id=" + id +
                ", value=" + value +
                ", type=" + type +
                ", handy=" + handy +
                ", length=" + length +
                ", width=" + width +
                ", material=" + material +
                '}';
    }
}
