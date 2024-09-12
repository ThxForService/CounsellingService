package com.thxforservice.counselling.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class
CounselingReview extends BaseMemberEntity {

    @Id
    @Column(length = 45)
    private String gid;

    @Lob
    private String content;

    private int rating;
}
