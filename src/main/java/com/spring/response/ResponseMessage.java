package com.spring.response;

import com.spring.pojo.Files;

public class ResponseMessage {
  private String message;
  private Files file;

 

public Files getFile() {
	return file;
}

public void setFile(Files file) {
	this.file = file;
}

public ResponseMessage(String message, Files file) {
    this.message = message;
    this.file= file;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
