package com.thxforservice.counselling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCounseling is a Querydsl query type for Counseling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCounseling extends EntityPathBase<Counseling> {

    private static final long serialVersionUID = 1733989612L;

    public static final QCounseling counseling = new QCounseling("counseling");

    public final com.thxforservice.global.entities.QBaseEntity _super = new com.thxforservice.global.entities.QBaseEntity(this);

    public final NumberPath<Long> agree = createNumber("agree", Long.class);

    public final EnumPath<com.thxforservice.counselling.constants.CCase> cCase = createEnum("cCase", com.thxforservice.counselling.constants.CCase.class);

    public final StringPath cCaseDetail = createString("cCaseDetail");

    public final StringPath content = createString("content");

    public final EnumPath<com.thxforservice.counselling.constants.CReason> cReason = createEnum("cReason", com.thxforservice.counselling.constants.CReason.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> cSeq = createNumber("cSeq", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final StringPath empNo = createString("empNo");

    public final StringPath gid = createString("gid");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> rDate = createDate("rDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> rTime = createTime("rTime", java.time.LocalTime.class);

    public final EnumPath<com.thxforservice.counselling.constants.Status> status = createEnum("status", com.thxforservice.counselling.constants.Status.class);

    public final NumberPath<Long> studentNo = createNumber("studentNo", Long.class);

    public QCounseling(String variable) {
        super(Counseling.class, forVariable(variable));
    }

    public QCounseling(Path<? extends Counseling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCounseling(PathMetadata metadata) {
        super(Counseling.class, metadata);
    }

}

