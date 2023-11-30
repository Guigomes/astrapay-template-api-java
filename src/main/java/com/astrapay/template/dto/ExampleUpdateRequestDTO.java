package com.astrapay.template.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExampleUpdateRequestDTO extends ExampleRequestDTO {

	@Positive
	private long id;

}
