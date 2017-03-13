package com.ffbet.fase3.domain;

public enum FilesPath {

	
	FILES_AVATARS("src/main/resources/static/files/avatars"),
	FILES_TEAMS_COVER("src/main/resources/static/files/teams/covers"),
	FILES_TEAMS_LOGO("src/main/resources/static/files/teams/logos"),
	FILES_PROMOS("src/main/resources/static/files/promos");
	
	private final String text;
		
	/**
     * @param text
     */
    private FilesPath(final String text) {
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
