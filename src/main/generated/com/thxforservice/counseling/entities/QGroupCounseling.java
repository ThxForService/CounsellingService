package com.thxforservice.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupCounseling is a Querydsl query type for GroupCounseling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupCounseling extends EntityPathBase<GroupCounseling> {

    private static final long serialVersionUID = 1328819605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupCounseling groupCounseling = new QGroupCounseling("groupCounseling");

    public final com.thxforservice.global.entities.QBaseEntity _super = new com.thxforservice.global.entities.QBaseEntity(this);

    public final BooleanPath attend = createBoolean("attend");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath department = createString("department");

    public final StringPath email = createString("email");

    public final StringPath grade = createString("grade");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> pgmRegSeq = createNumber("pgmRegSeq", Long.class);

    public final QGroupProgram program;

    public final NumberPath<Long> studentNo = createNumber("studentNo", Long.class);

    public final StringPath username = createString("username");

    public QGroupCounseling(String variable) {
        this(GroupCounseling.class, forVariable(variable), INITS);
    }

    public QGroupCounseling(Path<? extends GroupCounseling> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupCounseling(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupCounseling(PathMetadata metadata, PathInits inits) {
        this(GroupCounseling.class, metadata, inits);
    }

    public QGroupCounseling(Class<? extends GroupCounseling> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new QGroupProgram(forProperty("program")) : null;
    }

}

