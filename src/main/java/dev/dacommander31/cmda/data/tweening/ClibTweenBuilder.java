package dev.dacommander31.cmda.data.tweening;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Builds and plays a typed tween animation.
 * <p>
 * This builder allows configuration of the start and end values,
 * duration, easing, and interpolation strategy.
 *
 * @param <T> The type of value to interpolate.
 */

public class ClibTweenBuilder<T> {
    private final Consumer<T> setter;
    private Supplier<T> getter;
    private Supplier<T> endValue;
    private long duration = 20;
    private ClibEasings easing = ClibEasings.LINEAR;
    private ClibInterpolator<T> interp;
    private boolean useRealtimeIntep = true;

    protected ClibTweenBuilder(Consumer<T> setter, Class<T> type) {
        this.setter = setter;
        this.interp = ClibInterpolator.defaultFor(type);
    }

    public static <T> ClibTweenBuilder<T> of(Consumer<T> value, Class<T> type) {
        return new ClibTweenBuilder<>(value, type);
    }

    /**
     * Sets the starting value for the tween.
     *
     * @param value Supplier returning the start value (called once at play time)
     * @return The builder instance
     */
    public ClibTweenBuilder<T> from(Supplier<T> value) {
        this.getter = value;
        return this;
    }

    /**
     * Sets the end value the tween will reach.
     *
     * @param value Supplier returning the target value (called once at play time)
     * @return The builder instance
     */
    public ClibTweenBuilder<T> to(Supplier<T> value) {
        this.endValue = value;
        return this;
    }

    /**
     * Sets the duration of the tween in ticks.
     *
     * @param ticks The number of ticks over which the tween should complete
     * @return The builder instance
     */
    public ClibTweenBuilder<T> over(long ticks) {
        this.duration = ticks;
        return this;
    }

    public ClibTweenBuilder<T> withEasing(ClibEasings easing) {
        this.easing = easing;
        return this;
    }

    public ClibTweenBuilder<T> withInterp(ClibInterpolator<T> interp) {
        this.interp = interp;
        return this;
    }

    /**
     * Removes partial tick interpolation, meaning it will only interpolate every tick rather than every frame.
     *
     * @return The builder instance
     */
    public ClibTweenBuilder<T> noRealtimeInterp() {
        this.useRealtimeIntep = false;
        return this;
    }

    /**
     * Begins the tween, returning the running instance.
     *
     * @return The running tween instance
     * @throws IllegalStateException if from() or to() were not called
     */
    public ClibTween<T> play() {
        if (getter == null) {
            throw new IllegalStateException("from() must be called before play()");
        }
        if (endValue == null) {
            throw new IllegalStateException("to() must be called before play()");
        }

        ClibTween<T> tween = new ClibTween<>(getter.get(), endValue.get(), duration, setter, easing, interp, useRealtimeIntep);
        tween.start();
        return tween;
    }

}
