package com.ggemo.va.vascript;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VaCmdInfo {
    public static final VaCmdInfo FAIL_SYNTAX_ERROR_START_ERROR = new VaCmdInfo(false, VaScriptResponse.FAIL_SYNTAX_ERROR_START_ERROR, null, null);
    public static final VaCmdInfo FAIL_SYNTAX_ERROR_SPLIT_NAME_PARAM = new VaCmdInfo(false, VaScriptResponse.FAIL_SYNTAX_ERROR_SPLIT_NAME_PARAM, null, null);
    public static final VaCmdInfo FAIL_SYNTAX_ERROR_NAME_EMPTY = new VaCmdInfo(false, VaScriptResponse.FAIL_SYNTAX_ERROR_NAME_EMPTY, null, null);
    public static final VaCmdInfo FAIL_FUNC_NOT_EXIST = new VaCmdInfo(false, VaScriptResponse.FAIL_FUNC_NOT_EXIST, null, null);

    boolean result;
    VaScriptResponse errRes;
    String funcName;
    String[] params;
}
