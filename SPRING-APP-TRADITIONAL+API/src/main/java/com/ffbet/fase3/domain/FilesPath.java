package com.ffbet.fase3.domain;

public enum FilesPath {

	
	FILES_AVATARS("target/files/avatars"),
	FILES_TEAMS_COVER("target/files/teams/covers"),
	FILES_TEAMS_LOGO("target/files/teams/logos"),
	FILES_PROMOS("target/files/promos");
	
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
