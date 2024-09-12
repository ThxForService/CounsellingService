package com.thxforservice.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupProgram is a Querydsl query type for GroupProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupProgram extends EntityPathBase<GroupProgram> {

    private static final long serialVersionUID = 1375030140L;

    public static final QGroupProgram groupProgram = new QGroupProgram("groupProgram");

    public final com.thxforservice.global.entities.QBaseMemberEntity _super = new com.thxforservice.global.entities.QBaseMemberEntity(this);

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> currentCount = createNumber("currentCount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath Description = createString("Description");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath pgmNm = createString("pgmNm");

    public final NumberPath<Long> pgmSeq = createNumber("pgmSeq", Long.class);

    public final DateTimePath<java.time.LocalDateTime> programStartDate = createDateTime("programStartDate", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.thxforservice.counseling.constants.ProgramStatus> status = createEnum("status", com.thxforservice.counseling.constants.ProgramStatus.class);

    public QGroupProgram(String variable) {
        super(GroupProgram.class, forVariable(variable));
    }

    public QGroupProgram(Path<? extends GroupProgram> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupProgram(PathMetadata metadata) {
        super(GroupProgram.class, metadata);
    }

}

