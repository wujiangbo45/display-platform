package com.mapbar.display.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by suixin on 2017/5/19.
 */
public class LoginInCommand extends BaseCommand {

    @NotNull(message = "�û�������Ϊ��")
    @NotBlank(message = "�û�������Ϊ��")
    private String accountName;	//	��¼������

    @NotNull(message = "���벻��Ϊ��")
    @NotBlank(message = "���벻��Ϊ��")
    private String accountPwd;	//	����

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
