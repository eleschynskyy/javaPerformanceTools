package com.elearn.tests;

public class LogRecord {

	private String type;
	private String path;
	private String status;

	public String getType() {
		return type;
	}

	public LogRecord setType(String type) {
		this.type = type;
		return this;
	}

	public String getPath() {
		return path;
	}

	public LogRecord setPath(String path) {
		this.path = path;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public LogRecord setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogRecord other = (LogRecord) obj;
		return type.equals(other.type) && path.equals(other.path)
				&& status.equals(other.status);
	}

	@Override
	public String toString() {
		return getType() + "|" + getPath() + "|" + getStatus();
	}

}
