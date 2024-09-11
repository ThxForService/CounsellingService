package com.thxforservice.counselling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCounselingReview is a Querydsl query type for CounselingReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCounselingReview extends EntityPathBase<CounselingReview> {

    private static final long serialVersionUID = 1812862820L;

    public static final QCounselingReview counselingReview = new QCounselingReview("counselingReview");

    public final com.thxforservice.global.entities.QBaseMemberEntity _super = new com.thxforservice.global.entities.QBaseMemberEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath gid = createString("gid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public QCounselingReview(String variable) {
        super(CounselingReview.class, forVariable(variable));
    }

    public QCounselingReview(Path<? extends CounselingReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCounselingReview(PathMetadata metadata) {
        super(CounselingReview.class, metadata);
    }

}

