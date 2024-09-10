package com.thxforservice.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 1639768780L;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.thxforservice.global.entities.QBaseEntity _super = new com.thxforservice.global.entities.QBaseEntity(this);

    public final StringPath agree = createString("agree");

    public final EnumPath<com.thxforservice.reservation.constants.CCase> cCase = createEnum("cCase", com.thxforservice.reservation.constants.CCase.class);

    public final StringPath cCaseDetail = createString("cCaseDetail");

    public final StringPath content = createString("content");

    public final EnumPath<com.thxforservice.reservation.constants.CReason> cReason = createEnum("cReason", com.thxforservice.reservation.constants.CReason.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> cSeq = createNumber("cSeq", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final StringPath empNo = createString("empNo");

    public final StringPath gid = createString("gid");

    public final StringPath memberID = createString("memberID");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> rDate = createDate("rDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> rTime = createTime("rTime", java.time.LocalTime.class);

    public final EnumPath<com.thxforservice.reservation.constants.Status> status = createEnum("status", com.thxforservice.reservation.constants.Status.class);

    public final StringPath studentNo = createString("studentNo");

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

