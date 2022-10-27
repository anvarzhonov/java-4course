package ru.anvarzhonov.task2.process;

import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type FlowSkipFirstThreeDigits
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 14:30
 */
public class FlowSkipFirstThreeDigits implements FlowTransformer<Integer> {
    @Override
    public List<Integer> transformFlow() {
        List<Integer> randomDigits = FlowTransformer.fillArray();
        System.out.println("Входной массив чисел: " + randomDigits);
        List<Integer> result = new ArrayList<>();


        Observable.from(randomDigits)
            .skip(3)
            .subscribe(result::add);
        return result;
    }

    public void example() {
        Observable<Integer> dataSource = Observable.create(sub -> {
            for (int i = 0; i < 100; i++) {
                sub.onNext(i);
            }
        });

        dataSource.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });

    }
}
