package eu.yayi.core.entities;

import eu.yayi.commons.jpa.persistence.entities.ModelObject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Authors")
public class Author extends ModelObject<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    //@JoinTable(name = "Authors_Books")
    private Set<Book> books;

}
