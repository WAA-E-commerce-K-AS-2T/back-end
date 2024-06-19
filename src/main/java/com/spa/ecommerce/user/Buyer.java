package com.spa.ecommerce.user;

import com.spa.ecommerce.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Buyer extends User{


//    @OneToMany(mappedBy = "customer")
//    private Collection<Favorite> favorites;

}
