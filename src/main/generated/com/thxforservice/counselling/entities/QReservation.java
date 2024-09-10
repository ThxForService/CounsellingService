package com.thxforservice.counselling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Counselling> {

    private static final long serialVersionUID = 1639768780L;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.thxforservice.global.entities.QBaseEntity _super = new com.thxforservice.global.entities.QBaseEntity(this);

    public final NumberPath<Long> agree = createNumber("agree", Long.class);

    public final EnumPath<com.thxforservice.counselling.constants.CCase> cCase = createEnum("cCase", com.thxforservice.counselling.constants.CCase.class);

    public final StringPath cCaseDetail = createString("cCaseDetail");

    public final EnumPath<com.thxforservice.counselling.constants.CReason> cReason = createEnum("cReason", com.thxforservice.counselling.constants.CReason.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> cSeq = createNumber("cSeq", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final StringPath empNo = createString("empNo");

    public final StringPath memberID = createString("memberID");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> rDate = createDate("rDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> rTime = createDateTime("rTime", java.time.LocalDateTime.class);

    public final EnumPath<com.thxforservice.counselling.constants.Status> status = createEnum("status", com.thxforservice.counselling.constants.Status.class);

    public final StringPath studentNo = createString("studentNo");

    public QReservation(String variable) {
        super(Counselling.class, forVariable(variable));
    }

    public QReservation(Path<? extends Counselling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Counselling.class, metadata);
    }

}

