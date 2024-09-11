package com.thxforservice.counselling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCounsellingReview is a Querydsl query type for CounsellingReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCounsellingReview extends EntityPathBase<CounsellingReview> {

    private static final long serialVersionUID = 435185316L;

    public static final QCounsellingReview counsellingReview = new QCounsellingReview("counsellingReview");

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

    public QCounsellingReview(String variable) {
        super(CounsellingReview.class, forVariable(variable));
    }

    public QCounsellingReview(Path<? extends CounsellingReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCounsellingReview(PathMetadata metadata) {
        super(CounsellingReview.class, metadata);
    }

}

