package com.prinzasteria.coursesys.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "schools")
@NoArgsConstructor
@RequiredArgsConstructor
public class University {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name="name", nullable = false, unique = true)
    private String name;


}
