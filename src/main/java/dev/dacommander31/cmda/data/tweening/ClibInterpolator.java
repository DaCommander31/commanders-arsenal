package dev.dacommander31.cmda.data.tweening;

import net.minecraft.world.phys.Vec3;

import java.awt.*;

@FunctionalInterface
public interface ClibInterpolator<T> {
    T lerp(T start, T end, float u);


    ClibInterpolator<Float> FLOAT = (a, b, u) -> a + (b - a) * u;

    ClibInterpolator<Double> DOUBLE = (a, b, u) -> a + (b - a) * u;

    ClibInterpolator<Long> LONG = (a, b, u) -> (long) (a + (b - a) * u);

    ClibInterpolator<Integer> INT = (a, b, u) -> (int) (a + (b - a) * u);

    ClibInterpolator<Vec3> VEC3 = (a, b, u) ->
            new Vec3(
                    a.x + (b.x - a.x) * u,
                    a.y + (b.y - a.y) * u,
                    a.z + (b.z - a.z) * u
            );

    ClibInterpolator<Color> COLOR = (a, b, u) ->
            new Color(
                    a.getRed() + (b.getRed() - a.getRed()) * u,
                    a.getGreen() + (b.getGreen() - a.getGreen()) * u,
                    a.getBlue() + (b.getBlue() - a.getBlue()) * u,
                    a.getAlpha() + (b.getAlpha() - a.getAlpha()) * u
            );

    static <T> ClibInterpolator<T> defaultFor(Class<T> type) {
        ClibInterpolator<T> i = ClibInterpolatorRegistry.find(type);
        if (i == null)
            throw new IllegalArgumentException("No interpolator registered for " + type.getName());
        return i;
    }
}
