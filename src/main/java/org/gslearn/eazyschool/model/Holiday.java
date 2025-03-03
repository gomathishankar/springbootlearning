package org.gslearn.eazyschool.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.gslearn.eazyschool.enums.Type;

@Getter
@Setter
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {
    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;
}
