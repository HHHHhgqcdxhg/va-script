package com.ggemo.va.vascript;

import java.util.HashMap;
import java.util.Map;

public class VaScript {
    private Map<String, VaFunc> funcMap;

    public VaScript(VaFunc... vaFuncs) {
        funcMap = new HashMap<>();
        for (VaFunc vaFunc : vaFuncs) {
            funcMap.put(vaFunc.getFuncName().toLowerCase(), vaFunc);
        }

    }

    VaScriptResponse parse(String cmdStr, Object extraParam) {
        if (!cmdStr.startsWith("!") && !cmdStr.startsWith("！")) {
            return VaScriptResponse.FAIL_SYNTAX_ERROR_START_ERROR;
        }

        String[] splitedNameParam = cmdStr.substring(1).split("[:：]");
        if (splitedNameParam.length != 1 && splitedNameParam.length != 2) {
            return VaScriptResponse.FAIL_SYNTAX_ERROR_SPLIT_NAME_PARAM;
        }

        String funcName = splitedNameParam[0];
        if (funcName == null || funcName.isEmpty() || funcName.isBlank()) {
            return VaScriptResponse.FAIL_SYNTAX_ERROR_NAME_EMPTY;
        }

        funcName = funcName.toLowerCase();
        if (!funcMap.containsKey(funcName)) {
            return VaScriptResponse.FAIL_FUNC_NOT_EXIST;
        }

        String[] params;
        if (splitedNameParam.length == 1) {
            params = new String[0];
        } else {
            String paramStr = splitedNameParam[1];
            params = paramStr.split("\\|");
        }
        return funcMap.get(funcName).handle(params, extraParam);
    }

    VaScriptResponse parse(String cmdStr) {
        return parse(cmdStr, null);
    }
}
