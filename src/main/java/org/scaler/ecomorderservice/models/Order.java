package org.scaler.ecomorderservice.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@EntityListeners(value = AuditingEntityListener.class)
@Entity
@Table(name = "order_table" , indexes = { @Index(name = "user_index", columnList = "userId"), @Index(name = "product_index", columnList = "productId")})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    private Boolean isDeleted = false;

    private Long userId;

    private Long productId;

    private Double amount;

    private Integer quantity;

    private Double discount;

    private Date orderDate;
}
