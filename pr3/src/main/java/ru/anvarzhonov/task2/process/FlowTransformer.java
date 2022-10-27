package ru.anvarzhonov.task2.process;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type FlowTransformer
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 13:27
 */
public interface FlowTransformer<T> {
    boolean RANDOM_GENERATE_ACTIVE = false;
    int MIN_VALUE = 0, MAX_VALUE = 1000, ARRAY_SIZE = 10;

    List<T> transformFlow();

    static List<Integer> fillArray() {
        List<Integer> array = new ArrayList<>();
        if (RANDOM_GENERATE_ACTIVE) {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                array.add(RandomUtils.nextInt(MIN_VALUE, MAX_VALUE));
            }
        } else {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                array.add(i);
            }
        }
        return array;
    }
}
