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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_permission")
public class Permission extends IdEntity {

	private String permissionname;

	/*
	 * private Set<Subject> subjects = new HashSet<Subject>();
	 * 
	 * 
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "e_exam_subject", joinColumns = { @JoinColumn(name =
	 * "e_id", referencedColumnName = "id") }, inverseJoinColumns = {
	 * @JoinColumn(name = "s_id", referencedColumnName = "id") }) public
	 * Set<Subject> getSubjects() { return subjects; }
	 * 
	 * public void setSubjects(Set<Subject> subjects) { this.subjects =
	 * subjects; }
	 */
}
