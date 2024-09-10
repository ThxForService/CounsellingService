package com.thxforservice.reservation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupReserProgram is a Querydsl query type for GroupReserProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupReserProgram extends EntityPathBase<GroupReserProgram> {

    private static final long serialVersionUID = 700537750L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupReserProgram groupReserProgram = new QGroupReserProgram("groupReserProgram");

    public final BooleanPath approval = createBoolean("approval");

    public final BooleanPath attend = createBoolean("attend");

    public final StringPath department = createString("department");

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> pgmRegSeq = createNumber("pgmRegSeq", Long.class);

    public final QGroupProgram program;

    public final NumberPath<Long> studentNo = createNumber("studentNo", Long.class);

    public final StringPath username = createString("username");

    public QGroupReserProgram(String variable) {
        this(GroupReserProgram.class, forVariable(variable), INITS);
    }

    public QGroupReserProgram(Path<? extends GroupReserProgram> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupReserProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupReserProgram(PathMetadata metadata, PathInits inits) {
        this(GroupReserProgram.class, metadata, inits);
    }

    public QGroupReserProgram(Class<? extends GroupReserProgram> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new QGroupProgram(forProperty("program")) : null;
    }

}

