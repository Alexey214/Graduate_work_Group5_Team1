package pro.sky.graduate_work_group5_team1.util;

import java.util.Optional;

public interface UtilClassGraduate {

    default String methodName() {
        StackWalker walker = StackWalker.getInstance();
        Optional methodName = walker.walk(frames -> frames
                .skip(1)
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName));
        return methodName.get().toString();
    }

}
