package com.thxforservice.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupSchedule is a Querydsl query type for GroupSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupSchedule extends EntityPathBase<GroupSchedule> {

    private static final long serialVersionUID = -20577121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupSchedule groupSchedule = new QGroupSchedule("groupSchedule");

    public final com.thxforservice.global.entities.QBaseMemberEntity _super = new com.thxforservice.global.entities.QBaseMemberEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final QGroupCounseling program;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Long> schdlSeq = createNumber("schdlSeq", Long.class);

    public QGroupSchedule(String variable) {
        this(GroupSchedule.class, forVariable(variable), INITS);
    }

    public QGroupSchedule(Path<? extends GroupSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupSchedule(PathMetadata metadata, PathInits inits) {
        this(GroupSchedule.class, metadata, inits);
    }

    public QGroupSchedule(Class<? extends GroupSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new QGroupCounseling(forProperty("program")) : null;
    }

}

