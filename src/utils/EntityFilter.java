package utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityFilter {

    private EntityFilter() {}

    public static <T> List<T> filter(List<T> entities, Predicate<T> condition) {
        return entities.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    public static <T> List<T> sort(List<T> entities, Comparator<T> condition) {
        return entities.stream()
                .sorted(condition)
                .collect(Collectors.toList());
    }
}
