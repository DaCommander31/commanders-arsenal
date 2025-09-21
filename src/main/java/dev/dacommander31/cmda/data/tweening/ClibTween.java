package dev.dacommander31.cmda.data.tweening;

import java.util.function.Consumer;

public class ClibTween<T> {
    private final T start;
    private final T end;
    private T prev;
    private T curr;
    private final long duration;
    private final Consumer<T> setter;
    private final ClibEasings easing;
    private final ClibInterpolator<T> interp;
    private final boolean useRealtimeInterp;
    private long age = 0;

    protected ClibTween(T startValue, T endvalue, long duration, Consumer<T> setter, ClibEasings easing, ClibInterpolator<T> interp, boolean useRealtimeInterp) {
        this.start = startValue;
        this.end = endvalue;
        this.duration = duration;
        this.setter = setter;
        this.easing = easing;
        this.interp = interp;
        this.useRealtimeInterp = useRealtimeInterp;
        this.prev = startValue;
        this.curr = startValue;
    }

    public void start() {
        ClibTweenManager.add(this);
    }

    public boolean tick() {
        if (age++ >= duration) return true;
        prev = curr;
        float raw = (float) age / duration;
        float u = easing.apply(raw);
        curr = interp.lerp(start, end, u);
        return false;
    }

    public void renderLerp(float pt) {
        T visual = interp.lerp(prev, curr, pt);
        setter.accept(visual);
    }

    public boolean usesRealtimeInterp() {
        return useRealtimeInterp;
    }
}
