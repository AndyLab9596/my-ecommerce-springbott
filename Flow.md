Create User entity which names "users"
create table users -> 
Long id, String name, 

@Column(unique = true)
@NotBlank(message = "email is required")
String email;

@NotBlank(message = "Password is required")
String password, 

@Column(name = "phone_number")
@NotBlank(message = "Phone number is required")
String phoneNumber, 

UserRole role,

@OneToMany(mappedBy = "user", fetch = FetchType.Lazy, cascade = CascadeType.ALL)
List<OrderItem> orders,

@ManyToOne(fetch = FetchType.Lazy, CascadeType.ALL)
Address address, 

@Column(name = "created_at")
LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
then create 
UserRole enum ADMIN, USER
OrderStatus enum PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, RETURNED

--------------------------------------------------------
create Address entity 

Id Long id;

String street;
String city;
String state;
String zipcode;
String country;

@ManyToOne(fetch = FetchType.Lazy)
@JoinColumn(name = "user_id")
User user

@Column(name = "created_at")
LocalDateTime createdAt

--------------------------------------------------------
create Category entity
Id Long id; String name; 

@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JsonManagedReference
List<Product> productList;

LocalDateTime createdAt = LocalDateTime.now();


--------------------------------------------------------
create Product entity
Id Long id; String name, String description; String imageUrl; BigDecimal price; 

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
@JsonBackReference
Category category;

LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
create Order (name = "orders") entity
Long id， 
BigDecimal totalPrice, 
OneToMany(mappedBy = "order", fetch = FetchTye.LAZY, cascade = Cascade.ALL, orphanRemoval = true)
List<OrderItem> orderItemList, 
LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
create OrderItem (name = "order_items")
Long id, int quantity, BigDecimal price, OrderStatus status, 
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "name_id")
User user, 

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "product_id")
Product product

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "order_id")
Order order;

LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
create Payment(name = "Payment");
private Long id, BigDecimal amount; 
private String method;
private String status; 
@OneToOne(fetchType = "FetchType.LAZY")
@JoinColumn(name = "order_id")
private Order
LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
create Review 
private Long id, private String content, private int rating // 1 to 10,

@ManyToOne
@JoinColumn(name = "product_id")
Product product;

@ManyToOne
@JoinColumn(name = "user_id")
User user

LocalDateTime createdAt = LocalDateTime.now();

--------------------------------------------------------
DTO

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    String street;
    String city;
    String state;
    String zipcode;
    String country;
    UserDto user
}

@Data
public class CategoryDto {
    private Long id;
    String name;
    private List<ProductDto> productDtoList;
}

public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
    List<OrderItemDto> orderItemDtoList;
}

public class OrderItemDto {
    private Long id;
    private int quantity;
    private BigDecimal price;
    private String status
    private UserDto userDto;
    private ProductDto productDto;
}

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private CategoryDto categoryDto;
}

public class UserDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private List<OrderItemDto> orderItemDtoList;
    private AddressDto addressDto
}

--------------------------------------------------------

--------------------------------------------------------

OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {}

ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameOrDescriptionContaining(String name, String description);
}

--------------------------------------------------------
Create User -> copy access key, private key -> S3 













