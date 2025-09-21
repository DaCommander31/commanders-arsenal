package dev.dacommander31.cmda.data.tweening;

public enum ClibEasings implements Easing {
    LINEAR(t -> t),

    SINE_IN (t -> 1 - (float) Math.cos( t * (float)Math.PI / 2f )),
    SINE_OUT(t ->      (float) Math.sin( t * (float)Math.PI / 2f )),
    SINE_IN_OUT(t -> -.5f * ((float) Math.cos((float)Math.PI * t) - 1)),

    QUAD_IN (t -> t * t),
    QUAD_OUT(t -> 1 - (1 - t) * (1 - t)),
    QUAD_IN_OUT(t -> t < .5f ? 2*t*t
            : 1 - 2*(1-t)*(1-t)),

    CUBIC_IN (t -> t * t * t),
    CUBIC_OUT(t -> 1 - (float) Math.pow(1 - t, 3)),
    CUBIC_IN_OUT(t -> t < .5f
            ? 4 * t * t * t
            : 1 - (float) Math.pow(-2*t + 2, 3) / 2),

    QUART_IN (t -> t*t*t*t),
    QUART_OUT(t -> 1 - (float) Math.pow(1 - t, 4)),
    QUART_IN_OUT(t -> t < .5f
            ? 8 * t*t*t*t
            : 1 - (float) Math.pow(-2*t + 2, 4)/2),

    QUINT_IN (t -> t*t*t*t*t),
    QUINT_OUT(t -> 1 - (float) Math.pow(1 - t, 5)),
    QUINT_IN_OUT(t -> t < .5f
            ? 16 * t*t*t*t*t
            : 1 - (float) Math.pow(-2*t + 2, 5)/2),

    EXPO_IN (t -> t == 0 ? 0 : (float) Math.pow(2, 10*(t-1))),
    EXPO_OUT(t -> t == 1 ? 1 : 1 - (float) Math.pow(2, -10*t)),
    EXPO_IN_OUT(t -> {
        if (t == 0) return 0;
        if (t == 1) return 1;
        return t < .5f
                ? (float) Math.pow(2, 20*t - 10) / 2
                : (2 - (float) Math.pow(2, -20*t + 10)) / 2;
    }),

    CIRC_IN (t -> 1 - (float) Math.sqrt(1 - t*t)),
    CIRC_OUT(t ->   (float) Math.sqrt(1 - (t - 1)*(t - 1))),
    CIRC_IN_OUT(t -> t < .5f
            ? (1 - (float) Math.sqrt(1 - 4*t*t)) / 2
            : ((float) Math.sqrt(1 - (2*t - 2)*(2*t - 2)) + 1) / 2),

    BACK_IN (t -> {
        final float s = 1.70158f;
        return (s + 1) * t*t*t - s * t*t;
    }),
    BACK_OUT(t -> {
        final float s = 1.70158f;
        final float u = t - 1;
        return 1 + (s + 1)*u*u*u + s*u*u;
    }),
    BACK_IN_OUT(t -> {
        final float s = 1.70158f * 1.525f;
        return t < .5f
                ?  (.5f)*((s+1)*(2*t)*(2*t)*(2*t) - s*(2*t)*(2*t))
                :  .5f*((s+1)*(2*t-2)*(2*t-2)*(2*t-2) + s*(2*t-2)*(2*t-2) + 2);
    }),

    ELASTIC_IN (t -> {
        if (t == 0 || t == 1) return t;
        final float c4 = (2 * (float)Math.PI) / 3f;
        return - (float) Math.pow(2, 10*t - 10) * (float) Math.sin((t*10 - 10.75f)*c4);
    }),
    ELASTIC_OUT(t -> {
        if (t == 0 || t == 1) return t;
        final float c4 = (2 * (float)Math.PI) / 3f;
        return (float) Math.pow(2, -10*t) * (float) Math.sin((t*10 - .75f)*c4) + 1;
    }),
    ELASTIC_IN_OUT(t -> {
        if (t == 0 || t == 1) return t;
        final float c5 = (2 * (float)Math.PI) / 4.5f;
        if (t < .5f)
            return -.5f * (float) Math.pow(2, 20*t - 10) *
                    (float) Math.sin((20*t - 11.125f)*c5);
        return  (float) Math.pow(2, -20*t + 10) *
                (float) Math.sin((20*t - 11.125f)*c5) * .5f + 1;
    }),

    BOUNCE_OUT(t -> {
        final float n1 = 7.5625f, d  = 2.75f;
        if (t < 1 / d)              return n1 * t*t;
        else if (t < 2 / d) {
            t -= 1.5f/d;  return n1 * t*t + .75f;
        } else if (t < 2.5 / d) {
            t -= 2.25f/d; return n1 * t*t + .9375f;
        } else {
            t -= 2.625f/d; return n1 * t*t + .984375f;
        }
    }),
    BOUNCE_IN (t -> 1 - BOUNCE_OUT.apply(1 - t)),
    BOUNCE_IN_OUT(t -> t < .5f
            ? (1 - BOUNCE_OUT.apply(1 - 2*t)) * .5f
            :  (.5f) + BOUNCE_OUT.apply(2*t - 1) * .5f),

    INSTANT(t -> t < 1f ? 0f : 1f);

    

    private final Easing fn;
    ClibEasings(Easing fn) {
        this.fn = fn;
    }
    @Override
    public float apply(float t) {
        return fn.apply(t);
    }
}
