package com.ggemo.va.vascript;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class VaScriptTest {
    public static void main(String[] args) {
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        class UserNameObj {
            long userId;
            String userName;
        }

        VaFunc addFunc = new VaFunc() {
            private final TypeEnum[] TYPE_ENUMS = new TypeEnum[]{TypeEnum.NUMBER, TypeEnum.NUMBER};

            @Override
            public TypeEnum[] getParamsType() {
                return TYPE_ENUMS;
            }

            @Override
            public String getFuncName() {
                return "add";
            }

            @Override
            public Class getExtraParamType() {
                return UserNameObj.class;
            }

            @Override
            public VaScriptResponse handle(Object[] params, Object extraParam) {
                float a = (float) params[0];
                float b = (float) params[1];
                float res = a + b;
                UserNameObj userNameObj = (UserNameObj) extraParam;
                String resStr = userNameObj.toString() + ": " + res;
                return VaScriptResponse.success(resStr);
            }
        };

        VaScript vaScript = new VaScript(addFunc);

        Scanner sc = new Scanner(System.in);

        System.out.print("input your name: ");

        String name = sc.next();
        UserNameObj user = new UserNameObj(1145141919810L, name);

        while (true) {
            System.out.print("input cmd (supported add, with 2 number param): ");
            String cmd = sc.next();
            VaCmdInfo cmdInfo = vaScript.parseInfo(cmd);
            VaScriptResponse res;
            if (cmdInfo.getFuncName().toLowerCase().equals("add")) {
                res = vaScript.parse(cmdInfo, user);
            }else{
                res = vaScript.parse(cmdInfo, null);
            }
            System.out.println(res);
        }
    }
}