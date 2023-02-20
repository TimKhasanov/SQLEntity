import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private int age;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @Column(name = "registration_date")
    private Date registrationDate;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Subscription> subscription;

}

