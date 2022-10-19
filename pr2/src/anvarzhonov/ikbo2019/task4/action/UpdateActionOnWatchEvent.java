package anvarzhonov.ikbo2019.task4.action;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class UpdateActionOnWatchEvent implements ActionOnWatchEventService {
    Map<Path, String> filesMap = new HashMap<>();

    @Override
    public void doAction() {

    }
}
