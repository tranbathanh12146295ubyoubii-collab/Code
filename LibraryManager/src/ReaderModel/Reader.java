package ReaderModel;

import java.time.LocalDate;

public class Reader {
    private String id;
    private String name;
    private String sex;
    private LocalDate birth;
    private String cccd;
    private String address;
    private String numberPhone;
    private boolean available;

    public Reader(String id, String name, String sex, LocalDate birth, String address, String cccd, String numberPhone) {
        this.birth = birth;
        this.cccd = cccd;
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.sex = sex;
        this.address = address;
        available = true;
    }
    

    public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public String getCccd() {
        return cccd;
    }
    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNumberPhone() {
        return numberPhone;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
  
}
