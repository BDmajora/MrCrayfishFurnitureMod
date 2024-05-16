/*
 * Decompiled with CFR 0.152.
 */
package com.mrcrayfish.furniture.api;

import java.util.HashMap;

public class RecipeVariables
extends HashMap<String, Object> {
    public RecipeVariables addValue(String variable, Object object) {
        this.put(variable, object);
        return this;
    }
}

