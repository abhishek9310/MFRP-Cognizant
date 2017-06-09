package com.mms.model;

public class IdDocumentTO {
	private String idDocumentId;
	private String documentType;
	private String prefix;

	public IdDocumentTO() {
		idDocumentId="";
		documentType="";
		prefix="";
	}

	public IdDocumentTO(String idDocumentId) {
		this.idDocumentId = idDocumentId;
		documentType="";
		prefix="";
	}

	public String getIdDocumentId() {
		return idDocumentId;
	}

	public void setIdDocumentId(String idDocumentId) {
		this.idDocumentId = idDocumentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
