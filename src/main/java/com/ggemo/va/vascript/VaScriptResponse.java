package com.ggemo.va.vascript;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class VaScriptResponse {
    public final static VaScriptResponse FAIL_FUNC_NOT_EXIST = new VaScriptResponse(false, "func not exist");
    public final static VaScriptResponse FAIL_SYNTAX_ERROR_SPLIT_NAME_PARAM = new VaScriptResponse(false, "syntax error, at most one \":\"");
    public final static VaScriptResponse FAIL_SYNTAX_ERROR_NAME_EMPTY = new VaScriptResponse(false, "syntax error, func name should not be empty");
    public final static VaScriptResponse FAIL_SYNTAX_ERROR_START_ERROR = new VaScriptResponse(false, "syntax error, command should start with \"!\"");
    public final static VaScriptResponse FAIL_PARAM_ERROR = new VaScriptResponse(false, "param count or type not fit");
    public final static VaScriptResponse FAIL_EXTRA_PARAM_ERROR = new VaScriptResponse(false, "extra param not fit");

    public static VaScriptResponse success(String msg) {
        return new VaScriptResponse(true, msg);
    }

    boolean success;
    String res;
}
