package com.spa.ecommerce.user;

import com.spa.ecommerce.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Buyer extends User{


}
