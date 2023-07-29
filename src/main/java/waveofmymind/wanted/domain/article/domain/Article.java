package waveofmymind.wanted.domain.article.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import waveofmymind.wanted.global.domain.BaseTimeEntity;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "article")
@NoArgsConstructor(access = PROTECTED)
@Builder
@Getter
@AllArgsConstructor
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 빈칸일 수 없습니다.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "내용은 빈칸일 수 없습니다.")
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
