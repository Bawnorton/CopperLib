package com.bawnorton.copperlib.util;

@FunctionalInterface
public interface Selection<T> {
    boolean select(T object);

    @SafeVarargs
    static <T> Selection<T> and(Selection<T> selection, Selection<T>... other) {
        return object -> {
            for (Selection<T> s : other) {
                if (!s.select(object)) return false;
            }
            return selection.select(object);
        };
    }

    @SafeVarargs
    static <T> Selection<T> or(Selection<T> selection, Selection<T>... other) {
        return object -> {
            for (Selection<T> s : other) {
                if (s.select(object)) return true;
            }
            return selection.select(object);
        };
    }

    static <T> Selection<T> not(Selection<T> selection) {
        return object -> !selection.select(object);
    }

    static <T> Selection<T> always() {
        return object -> true;
    }

    static <T> Selection<T> never() {
        return object -> false;
    }
}
