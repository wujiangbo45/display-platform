package com.mapbar.display.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by suixin on 2017/5/19.
 */
public class LoginInCommand extends BaseCommand {

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String accountName;	//	登录者名称

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String accountPwd;	//	密码

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }
}
