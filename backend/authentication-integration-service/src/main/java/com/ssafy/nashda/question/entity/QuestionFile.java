package com.ssafy.nashda.question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class QuestionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "questionfile_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_index", nullable = false)
    private Question question;

    @Column(length = 1000)
    private String url;

    private String fileName;

    @Builder
    public QuestionFile(Question question, String url, String fileName) {
        this.question = question;
        this.url = url;
        this.fileName = fileName;
    }
}
