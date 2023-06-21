package com.admin.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class Auditable {
	
	@Column(name = "CREATED_AT",updatable = false)
	public LocalDateTime caretedAt;
	
	@Column(name = "UPDATED_AT",insertable = true)
	public LocalDateTime updatedAt;
	
	@Column(name = "CREATED_BY")
	public String createdBy;
	
	@Column(name = "UPDATED_BY")
	public String updatedBy;
	
}
