package dev.dacommander31.cmda.data.tweening;

import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClibInterpolatorRegistry {
    private static final Map<Class<?>, ClibInterpolator<?>> MAP = new ConcurrentHashMap<>();

    public static <T> void register(Class<T> type, ClibInterpolator<T> interp) {
        MAP.put(type, interp);
    }

    @SuppressWarnings("unchecked")
    public static <T> ClibInterpolator<T> find(Class<T> type) {
        return (ClibInterpolator<T>) MAP.get(type);
    }

    static {
        register(Float.class, ClibInterpolator.FLOAT);
        register(Double.class, ClibInterpolator.DOUBLE);
        register(Long.class, ClibInterpolator.LONG);
        register(Integer.class, ClibInterpolator.INT);
        register(Vec3.class, ClibInterpolator.VEC3);
        register(Color.class, ClibInterpolator.COLOR);
    }
}
