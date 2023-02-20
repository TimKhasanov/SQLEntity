import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {
    @Getter
    @Setter
    @EmbeddedId
    private Key id;
    @Getter
    @Setter
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
    @Getter
    @Setter
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Key implements Serializable {

        @Getter
        @Setter
        @Column(name = "student_name")
        private String studentName;

        @Getter
        @Setter
        @Column(name = "course_name")
        private String courseName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(studentName, key.studentName) && Objects.equals(courseName, key.courseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, courseName);
        }
    }
}

