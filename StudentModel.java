package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student_Information")
public class StudentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private byte [] certificateFile;
	private byte [] passportimage;
	
	

	public byte[] getCertificateFile() {
		return certificateFile;
	}

	public void setCertificateFile(byte[] certificateFile) {
		this.certificateFile = certificateFile;
	}

	public byte[] getPassportimage() {
		return passportimage;
	}

	public void setPassportimage(byte[] passportimage) {
		this.passportimage = passportimage;
	}

	public StudentModel() {
		super();
	}

	public StudentModel(int id) {
		super();
		this.id = id;
	}
	
	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}

	public StudentModel(String name, String email, String phone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	

	public StudentModel(int id, String name, String email, String phone, String password, byte[] certificateFile,
			byte[] passportimage) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.certificateFile = certificateFile;
		this.passportimage = passportimage;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
