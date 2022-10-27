package ru.anvarzhonov.task2.process;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type Generate
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 12:28
 */
public class NumbersToSquareNumbersFlow implements FlowTransformer<Integer> {
    @Override
    public List<Integer> transformFlow() {
        List<Integer> digits = FlowTransformer.fillArray();

        List<Integer> result = new ArrayList<>();

        Observable.from(digits)
            .map(d -> d * d)
            .subscribe(result::add);

        return result;
    }
}
