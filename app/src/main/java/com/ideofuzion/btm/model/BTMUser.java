package com.ideofuzion.btm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Zohaib Khaliq on 4/11/2017.
 */

public class BTMUser {


    @SerializedName("_id")
    private String userId;
    @SerializedName("userEthereumId")
    private String userBitcoinId;
    @SerializedName("maximumHotWalletBalance")
    private String maximumHotWalletBalance;
    @SerializedName("minimumHotWalletBalance")
    private String minimumHotWalletBalance;
    @SerializedName("updatedOnUTC")
    private String updatedOnUTC;
    @SerializedName("createdOnUTC")
    private String createdOnUTC;
    @SerializedName("userRole")
    private String userRole;
    @SerializedName("userPassword")
    private String userPassword;
    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("userName")
    private String userName;
    @SerializedName("ethereumUserPasscode")
    private String ethereumUserPasscode;
    @SerializedName("krakenAPIKey")
    private String krakenAPIKey;
    @SerializedName("krakenAPISecret")
    private String krakenAPISecret;
    @SerializedName("profitWalletAddress")
    private String profitWalletAddress;

    public String getBitcoinDollarRate() {
        return bitcoinDollarRate;
    }

    public void setBitcoinDollarRate(String bitcoinDollarRate) {
        this.bitcoinDollarRate = bitcoinDollarRate;
    }

    private String bitcoinDollarRate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBitcoinId() {
        return userBitcoinId;
    }

    public void setUserBitcoinId(String userBitcoinId) {
        this.userBitcoinId = userBitcoinId;
    }

    public String getMaximumHotWalletBalance() {
        return maximumHotWalletBalance;
    }

    public void setMaximumHotWalletBalance(String maximumHotWalletBalance) {
        this.maximumHotWalletBalance = maximumHotWalletBalance;
    }

    public String getMinimumHotWalletBalance() {
        return minimumHotWalletBalance;
    }

    public void setMinimumHotWalletBalance(String minimumHotWalletBalance) {
        this.minimumHotWalletBalance = minimumHotWalletBalance;
    }

    public String getUpdatedOnUTC() {
        return updatedOnUTC;
    }

    public void setUpdatedOnUTC(String updatedOnUTC) {
        this.updatedOnUTC = updatedOnUTC;
    }

    public String getCreatedOnUTC() {
        return createdOnUTC;
    }

    public void setCreatedOnUTC(String createdOnUTC) {
        this.createdOnUTC = createdOnUTC;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEthereumUserPasscode() {
        return ethereumUserPasscode;
    }

    public void setEthereumUserPasscode(String ethereumUserPasscode) {
        this.ethereumUserPasscode = ethereumUserPasscode;
    }

    public String getKrakenAPIKey() {
        return krakenAPIKey;
    }

    public void setKrakenAPIKey(String krakenAPIKey) {
        this.krakenAPIKey = krakenAPIKey;
    }

    public String getKrakenAPISecret() {
        return krakenAPISecret;
    }

    public void setKrakenAPISecret(String krakenAPISecret) {
        this.krakenAPISecret = krakenAPISecret;
    }

    public String getProfitWalletAddress() {
        return profitWalletAddress;
    }

    public void setProfitWalletAddress(String profitWalletAddress) {
        this.profitWalletAddress = profitWalletAddress;
    }

    public String getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(String isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    @SerializedName("isEmailVerified")
    private String isEmailVerified;

}
