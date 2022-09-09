package site.workforus.forus.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDTO<T> {
	private String result;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	private T data;

	public ResponseDTO(String result, T data) {
		this.result = result;
		this.data = data;
	}

	public ResponseDTO(String result, int status, T data) {
		this.result = result;
		this.status = status;
		this.data = data;
	}

	public ResponseDTO(String result, int status, String message, T data) {
		this.result = result;
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
