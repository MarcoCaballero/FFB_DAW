package com.ffbet.fase3.domain;

public enum TemplatesPath {
	ADMIN_HOME("admin/home"),
	ADMIN_LOGIN("admin/login"),
	ADMIN_MATCH("admin/partidos"),
	ADMIN_TEAM("admin/equipos"),
	ADMIN_PROMOTION("admin/promociones"),
	ADMIN_RESULTS("admin/resultados");
	
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
