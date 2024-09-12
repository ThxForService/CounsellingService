package com.thxforservice.counseling.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupProgram is a Querydsl query type for GroupProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupProgram extends EntityPathBase<GroupCounseling> {

    private static final long serialVersionUID = 1375030140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupProgram groupProgram = new QGroupProgram("groupProgram");

    public final BooleanPath attend = createBoolean("attend");

    public final StringPath department = createString("department");

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> pgmRegSeq = createNumber("pgmRegSeq", Long.class);

    public final QGroupCounseling program;

    public final NumberPath<Long> studentNo = createNumber("studentNo", Long.class);

    public final StringPath username = createString("username");

    public QGroupProgram(String variable) {
        this(GroupCounseling.class, forVariable(variable), INITS);
    }

    public QGroupProgram(Path<? extends GroupCounseling> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupProgram(PathMetadata metadata, PathInits inits) {
        this(GroupCounseling.class, metadata, inits);
    }

    public QGroupProgram(Class<? extends GroupCounseling> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new QGroupCounseling(forProperty("program")) : null;
    }

}

