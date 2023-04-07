package com.example.models.restaurant;

import com.example.models.comments.Comments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String directorname;
    private String description;
    private Long rating;

    private boolean isParking;

    private boolean isWifi;

    private Long minprice;
    private Long maxprice;

    private Long averageprice;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Comments comments;
}
