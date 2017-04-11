package com.ffbet.fase3.domain;

public enum PromotionType {

	
	PROMO_DISCCOUNT("BONODESCUENTO"),
	PROMO_GIFT("PROMOCIONREGALO");
	
	private final String text;
		
	/**
     * @param text
     */
    private PromotionType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
