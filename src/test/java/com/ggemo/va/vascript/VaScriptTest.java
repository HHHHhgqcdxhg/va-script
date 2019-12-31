package com.ggemo.va.vascript;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

class VaScriptTest {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserIdObj{
        long userId;
    }

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
            public Class getExtraParamType() {
                return UserIdObj.class;
            }

            @Override
            public VaScriptResponse handle(Object[] params, Object extraParam) {
                float a = (float) params[0];
                float b = (float) params[1];
                float res = a + b;
                UserIdObj userIdObj = (UserIdObj) extraParam;
                String resStr = userIdObj.getUserId() + ": " + res;
                return VaScriptResponse.success(resStr);
            }
        };

        VaScript vaScript = new VaScript(addFunc);

        VaScriptResponse res = vaScript.parse("!addFunc:1|2.5", new UserIdObj(123L));
        System.out.println(res);
    }
}