package io.github.crudapp.model.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Book extends AbstractBook {
    private Long id;

    public Book(Long id, String title, String author, Double price) {
        super(title, author, price);
        this.id = id;
    }

}
