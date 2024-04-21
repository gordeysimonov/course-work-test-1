package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Rating rate;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;
    @ManyToOne
    @JoinColumn(nullable = false)
    private MusicFile musicFileId;

    public enum Rating {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10);

        private final int value;

        Rating(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

}
