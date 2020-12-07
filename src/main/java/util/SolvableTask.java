package util;

import java.lang.invoke.MethodHandles;

public interface SolvableTask extends Solvable, Task {
    @Override
    default String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }
}
