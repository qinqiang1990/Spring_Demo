package shrio;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table; 

@Entity
@Table(name = "user")
public class User extends IdEntity {
 
  private String name;

  private String passwd;
 
	/*
	 * @OneToOne(cascade = CascadeType. ALL)
	 * 
	 * @JoinColumn(name="add_id") public Address getAddress() { return address;
	 * }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  } 


}
