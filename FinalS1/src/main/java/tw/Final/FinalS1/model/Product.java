package tw.Final.FinalS1.model;



import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private Long id;
	 	
	 	@Column(name = "name", nullable = false)
	 	private String name;
	 	
	 	@Column(name = "description")
	 	private String description;
	 	
	 	@ManyToOne
	    @JoinColumn(name = "category_id", nullable = false)
	 	//@JsonManagedReference(value = "category-products")
	 	private Category category;
	 	
	 	@ManyToOne
	    @JoinColumn(name = "type_id", nullable = false)
	 	//@JsonManagedReference(value = "type-products")
	 	private Type type;
	 	
	 	@Column(name = "price")
	 	private int  price;
	 	
	 	@Lob
	 	@Column(name = "image", columnDefinition = "LONGTEXT")
	 	private String image;
	 	
	 	@Column(name = "created_at", updatable = false)//創建紀錄不可被修改
	 	private Timestamp createdAt;
	 	
	 	@Column(name = "updated_at")
	 	private Timestamp updatedAt;
	 	
	 	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 	@JsonBackReference("product-variant")
	 	private List<ProductVariant> productVariants;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}

		public List<ProductVariant> getProductVariants() {
			return productVariants;
		}

		public void setProductVariants(List<ProductVariant> productVariants) {
			this.productVariants = productVariants;
		}
	 	
	 	
	
	
}
