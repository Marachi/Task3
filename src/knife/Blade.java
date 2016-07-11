package knife;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sviatoslav Potaychuk on 04.07.2016.
 */
@XmlRootElement(name = "Knife")
@XmlType(propOrder = {"type", "handy", "origin", "visual" , "value"})
public class Blade {

    /**
     * Id attribute
     */
    private int id;

    /**
     * Type of blade
     */
    private Type type;

    /**
     * Handy of blade
     */
    private Handy handy;

    /**
     * Country of origin
     */
    private String origin;

    /**
     * Visual properties
     */
    private List<Visual> visual = new LinkedList<>();

    /**
     * Collectible
     */
    private boolean value;

    /**
     * Default constructor
     */
    public Blade() {
    }


    /**
     * Constructor
     * @param id is Id
     * @param type is type of blade
     * @param handy is handy of blade
     * @param origin is country of origin
     * @param visual is list of visual properties
     * @param value is collectible of blade
     */
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

    /**
     * This class subscribes visual properties of blade
     */
    public static class Visual{

        /**
         * It's length of blade
         */
        @XmlElement(name = "Length")
        private double length;

        /**
         * It's width of blade
         */
        @XmlElement(name = "Width")
        private double width;

        /**
         * It's material of blade
         */
        @XmlElement(name = "Material")
        private Material material;

        /**
         * It's material of handle
         */
        @XmlElement(name = "Handle")
        private Handle handle;

        /**
         * It's existence of fuller
         */
        @XmlElement(name = "Fuller_exist")
        private boolean fuller;

        /**
         * Default constructor
         */
        public Visual() {
        }

        /**
         * Constructor
         * @param length is length
         * @param width is width
         * @param material is material of blade
         * @param handle is material of handle
         * @param fuller is existence of fuller
         */
        public Visual(double length, double width, Material material, Handle handle, boolean fuller) {
            this.length = length;
            this.width = width;
            this.material = material;
            this.handle = handle;
            this.fuller = fuller;
        }

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

        //getters & setters
        public void setLength(double length) {
            this.length = length;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public void setMaterial(Material material) {
            this.material = material;
        }

        public void setHandle(Handle handle) {
            this.handle = handle;
        }

        public void setFuller(boolean fuller) {
            this.fuller = fuller;
        }
    }

    //getters & setters
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
