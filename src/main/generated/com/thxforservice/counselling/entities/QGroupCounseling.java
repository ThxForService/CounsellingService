package com.thxforservice.counselling.entities;

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

    private static final long serialVersionUID = 1909614957L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupCounseling groupCounseling = new QGroupCounseling("groupCounseling");

    public final BooleanPath attend = createBoolean("attend");

    public final StringPath department = createString("department");

    public final StringPath grade = createString("grade");

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

