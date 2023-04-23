package ${package.Entity};

import lombok.Getter;

@Getter
public enum MessageCode {
    SUCCESS(0, "success"), FAIL(1001, "fail");
    private Integer code;
    private String message;

    private MessageCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageByCode(Integer code) {
        MessageCode[] values = MessageCode.values();
        for (MessageCode val : values) {
            if (val.getCode().equals(code)) {
                return val.getMessage();
            }
        }
        return "";
    }

}
