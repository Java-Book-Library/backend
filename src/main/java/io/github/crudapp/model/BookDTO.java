package io.github.crudapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String title;
    private String author;
    private double price;

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

}
