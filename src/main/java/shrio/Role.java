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
@Table(name = "t_role")
public class Role extends IdEntity {

	private String rolename;

	/*
	 * @OneToOne(mappedBy = "address") public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

}
