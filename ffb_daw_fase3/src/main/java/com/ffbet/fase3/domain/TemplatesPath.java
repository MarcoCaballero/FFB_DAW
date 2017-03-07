package com.ffbet.fase3.domain;

public enum TemplatesPath {
	ADMIN_HOME("admin/home"),
	ADMIN_MATCH("admin/partidos"),
	ADMIN_TEAM("admin/equipos"),
	ADMIN_PROMOTION("admin/promociones"),
	ADMIN_RESULTS("admin/resultados"),
	
	USER_HOME("user/index"),
	USER_SPORT_BET("user/apuestasdeportivas"),
	USER_EGAMES_BET("user/apuestasesports"),
	USER_USER_ACCOUNT("user/micuenta"),
	USER_PROMOTIONS("user/promos"),
	USER_ADD_CREDIT("user/depositar"),
	USER_GET_CREDIT("user/retirar"),
	USER_SEE_POLICY("user/politica"),
	
	MAIN_REG("user/reg"),
	MAIN_LOGIN("user/login");
	
	private final String text;
		
	/**
     * @param text
     */
    private TemplatesPath(final String text) {
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
