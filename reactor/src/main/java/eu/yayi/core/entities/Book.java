package eu.yayi.core.entities;

import eu.yayi.commons.jpa.persistence.entities.ModelObject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Books")
public class Book extends ModelObject<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    private Set<Author> authors;


}
