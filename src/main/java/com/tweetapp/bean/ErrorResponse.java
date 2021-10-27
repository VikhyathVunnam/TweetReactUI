package com.tweetapp.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = -5998300576105749889L;

	private String errMessage;
	private String errDetails;
	private int statusCode;
	private LocalDateTime timeStamp;
}
