package com.bawnorton.copperlib.util.tuple;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public final class Pair<A, B> {
    private final A first;
    private final B second;

    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    public String asString() {
        return first.toString() + "_" + second.toString();
    }
}
