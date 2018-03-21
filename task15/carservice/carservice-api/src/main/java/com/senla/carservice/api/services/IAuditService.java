package com.senla.carservice.api.services;

import com.senla.carservice.model.beans.Audit;

public interface IAuditService {
	void addAudit(Audit audit);

	boolean removeAudit(Audit audit);
}
