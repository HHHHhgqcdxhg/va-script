package com.ggemo.va.vascript;

import org.junit.jupiter.api.Test;

class VaScriptTest {

    @Test
    void test() {
        VaFunc addFunc = new VaFunc() {
            private final TypeEnum[] TYPE_ENUMS = new TypeEnum[]{TypeEnum.NUMBER, TypeEnum.NUMBER};

            @Override
            public TypeEnum[] getParamsType() {
                return TYPE_ENUMS;
            }

            @Override
            public String getFuncName() {
                return "addFunc";
            }

            @Override
            public VaScriptResponse handle(Object[] params) {
                float a = (float) params[0];
                float b = (float) params[1];
                float res = a + b;
                return VaScriptResponse.success(String.valueOf(res));
            }
        };

        VaScript vaScript = new VaScript(addFunc);

        VaScriptResponse res = vaScript.parse("!addFunc:1|2.5");
        System.out.println(res);
    }
}