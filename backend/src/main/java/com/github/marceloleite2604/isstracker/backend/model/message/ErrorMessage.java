package com.github.marceloleite2604.isstracker.backend.model.message;

import com.figtreelake.blimp.Message;
import java.io.File;

public enum ErrorMessage implements Message {

  STATUS_CODE_MESSAGE_EXCHANGING_ERROR(1),
  ERROR_EXCHANGING(2),
  ERROR_RETRIEVING_ISS_LOCATION_NOW(3),
  ERROR_RETRIEVING_ISS_PASS_TIMES(4),
  ERROR_RETRIEVING_ISS_ASTRONAUTS(5),
  ERROR_RETRIEVING_MAP(6);

  public static final String FILE_PATH = "error/error".replace("/", File.separator);

  public static final String MESSAGE_CODE_TEMPLATE = "error.%d";

  private int code;

  private ErrorMessage(int code) {
    this.code = code;
  }

  public String getCode() {
    return String.format(MESSAGE_CODE_TEMPLATE, code);
  }

}
