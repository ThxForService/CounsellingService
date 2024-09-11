package com.thxforservice.counselling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupCounselling is a Querydsl query type for GroupCounselling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupCounselling extends EntityPathBase<GroupCounselling> {

    private static final long serialVersionUID = -931393589L;

    public static final QGroupCounselling groupCounselling = new QGroupCounselling("groupCounselling");

    public final com.thxforservice.global.entities.QBaseMemberEntity _super = new com.thxforservice.global.entities.QBaseMemberEntity(this);

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

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

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.thxforservice.counselling.constants.ProgramStatus> status = createEnum("status", com.thxforservice.counselling.constants.ProgramStatus.class);

    public QGroupCounselling(String variable) {
        super(GroupCounselling.class, forVariable(variable));
    }

    public QGroupCounselling(Path<? extends GroupCounselling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupCounselling(PathMetadata metadata) {
        super(GroupCounselling.class, metadata);
    }

}

