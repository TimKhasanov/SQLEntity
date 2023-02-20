import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Getter
    @Setter
    @EmbeddedId
    private Key id;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student studentId;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course courseId;
    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;



}
