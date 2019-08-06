package com.wukj.general.library.af.results;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/24 下午9:06
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/24 下午9:06
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.无参数有返回值
 * 2.
 * 3.
 */
public abstract class FunctionNoParamWithResult<Result> extends Function {


    public FunctionNoParamWithResult(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract Result function();


}
