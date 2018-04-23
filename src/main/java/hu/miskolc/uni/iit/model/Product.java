package hu.miskolc.uni.iit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Alex Toth
 *
 */
@Entity
@Table(name = "product")
@NamedQueries({
	@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "Product.lastInsertId", query = "SELECT last_insert_id() FROM DUAL")
})
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "parts",
		joinColumns = @JoinColumn(name = "product", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "part", referencedColumnName = "id")
	)
	private List<Product> parts;

}
