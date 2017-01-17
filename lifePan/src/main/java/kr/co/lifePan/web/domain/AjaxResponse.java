package kr.co.lifePan.web.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AjaxResponse {
	private boolean success;
	private String message;
	private Long totalCount;
	private Long pageNo;
	private Long pageSize;
	private HttpStatus status;
	private Map<String, String> errors;
	private Map<String, Object> map;
	private List<? extends Object> data;

	public AjaxResponse() {
		this.success = true;
		this.status = HttpStatus.OK;
	}

	@JsonIgnore
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void put(String name, Object value) {
		if (this.map == null) {
			this.map = new HashMap<String, Object>();
		}
		this.map.put(name, value);
	}
	
	public Object get(String name) {
		if (this.map == null) {
			this.map = new HashMap<String, Object>();
		}
		return this.map.get(name);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void addBindingResult(BindingResult bindingResult) {
		if (bindingResult != null && bindingResult.hasErrors()) {
			createErrorsMap();
			for (ObjectError error : bindingResult.getAllErrors()) {
				String key = (error instanceof FieldError ? ((FieldError) error)
						.getField() : error.getObjectName());
				errors.put(key, error.getDefaultMessage());
			}
		}
	}

	public void setBindingResult(BindingResult bindingResult) {
		removeErrorsMap();
		if (bindingResult != null && bindingResult.hasErrors()) {
			createErrorsMap();
			for (ObjectError error : bindingResult.getAllErrors()) {
				String key = (error instanceof FieldError ? ((FieldError) error)
						.getField() : error.getObjectName());
				errors.put(key, error.getDefaultMessage());
			}
		}
	}

	public void clearErrors() {
		if (errors != null) {
			errors.clear();
		}
		errors = null;
	}

	private void createErrorsMap() {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
	}

	private void removeErrorsMap() {
		if (errors != null) {
			errors.clear();
		}
		errors = null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
