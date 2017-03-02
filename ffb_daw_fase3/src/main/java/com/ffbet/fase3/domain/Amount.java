/**
 * 
 */
package com.ffbet.fase3.domain;

/**
 * @author Marco
 *
 */
public enum Amount {
	EUR_1(1),
	EUR_5(5),
	EUR_10(10),
	EUR_25(25),
	EUR_50(50);
	
    private final int value;

    Amount(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
