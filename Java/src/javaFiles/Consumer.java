package javaFiles;

public class Consumer {
    private int id;
    private String name, area, city, type, password;

    public Consumer(int id, String name, String area, String city, String type, String password) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.city = city;
        this.type = type;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Consumer [id=" + id + ", name=" + name + ", area=" + area + ", city=" + city + ", type=" + type + "]";
    }

}
