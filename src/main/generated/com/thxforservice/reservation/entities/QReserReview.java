package com.thxforservice.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReserReview is a Querydsl query type for ReserReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserReview extends EntityPathBase<ReserReview> {

    private static final long serialVersionUID = 612872709L;

    public static final QReserReview reserReview = new QReserReview("reserReview");

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

    public QReserReview(String variable) {
        super(ReserReview.class, forVariable(variable));
    }

    public QReserReview(Path<? extends ReserReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReserReview(PathMetadata metadata) {
        super(ReserReview.class, metadata);
    }

}

