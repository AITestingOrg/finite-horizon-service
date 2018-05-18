package org.aist.aide.finitehorizonservice.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.aist.aide.finitehorizonservice.domain.models.AdditionalValue;
import org.aist.aide.finitehorizonservice.domain.models.FiniteType;

public class TestsConstants {
    public static final String type = "type";
    public static final String existingValue = "2";
    public static final String newValue = "99";
    public static final AdditionalValue additionalValue = new AdditionalValue(newValue);
    public static final Set<String> values = new HashSet<>(Arrays.asList("1", "2"));
    public static final FiniteType finiteType = new FiniteType(type, values);

    public static final String type2 = "type2";
    public static final Set<String> values2 = new HashSet<>(Arrays.asList("2", "3", "4"));
    public static final FiniteType finiteType2 = new FiniteType(type2, values2);
}
