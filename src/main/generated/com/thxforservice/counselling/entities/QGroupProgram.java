package com.thxforservice.counselling.entities;

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
public class QGroupProgram extends EntityPathBase<GroupProgram> {

    private static final long serialVersionUID = -1465098588L;

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
        this(GroupProgram.class, forVariable(variable), INITS);
    }

    public QGroupProgram(Path<? extends GroupProgram> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupProgram(PathMetadata metadata, PathInits inits) {
        this(GroupProgram.class, metadata, inits);
    }

    public QGroupProgram(Class<? extends GroupProgram> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new QGroupCounseling(forProperty("program")) : null;
    }

}

