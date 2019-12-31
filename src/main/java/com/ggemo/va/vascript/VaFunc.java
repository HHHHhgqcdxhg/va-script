package com.ggemo.va.vascript;

import java.util.regex.Pattern;

public abstract class VaFunc {
    public abstract TypeEnum[] getParamsType();

    public abstract String getFuncName();

    public abstract Class getExtraParamType();

//    public abstract VaScriptResponse handle(Object[] params);

    public abstract VaScriptResponse handle(Object[] params, Object extraParam);

    public VaScriptResponse handle(String[] params, Object extraParam){
        Object[] objects = vertify(params);
        if (objects == null) {
            return VaScriptResponse.FAIL_PARAM_ERROR;
        }
        if(!vertifyExtra(extraParam)){
            return VaScriptResponse.FAIL_EXTRA_PARAM_ERROR;
        }
        return handle(objects, extraParam);
    };
//
//    public VaScriptResponse handle(String[] params){
//        Object[] objects = vertify(params);
//        if (objects == null) {
//            return VaScriptResponse.FAIL_PARAM_ERROR;
//        }
//        return handle(objects);
//    }

    private static final Pattern NUMBER_REGIX = Pattern.compile("-?([1-9][0-9]*|0)(\\.[0-9]+)?");

    /**
     * vertify. 成功的话将param转换成对应的类型,返回param的数组, 不成功的话返回null
     * @param params: params
     * @return Object数组
     */
    public Object[] vertify(String[] params){
        if (params.length != getParamsType().length) {
            return null;
        }
        Object[] objects = new Object[getParamsType().length];
        for (int i = 0; i < params.length; i++) {
            String s = params[i];
            TypeEnum type = getParamsType()[i];
            if (type.equals(TypeEnum.NUMBER)) {
                if (NUMBER_REGIX.matcher(s).matches()) {
                    Float f = Float.valueOf(s);
                    objects[i] = f;
                }else{
                    return null;
                }
            }else{
                objects[i] = s;
            }
        }
        return objects;
    }

    public boolean vertifyExtra(Object object){
        if (this.getExtraParamType() == null) {
            return true;
        }
        return object.getClass() == this.getExtraParamType();
    }

    public static void main(String[] args) {

    }
}
