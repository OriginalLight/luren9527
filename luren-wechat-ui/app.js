const api = require("config/config.js");

App({

  doLogin: function () {
    let that = this;
    let infoRes = wx.getStorageSync("infoRes");
    console.log(infoRes)
    wx.showLoading({
      title: "登录中",
    });
    wx.login({
      success: loginRes => {
        if (loginRes.code){
          wx.request({
            url: api.loginUrl,
            method: "POST",
            data: {
              code: loginRes.code,
              rawData: infoRes.rawData,
              signature: infoRes.signature,
              encryptedData: infoRes.encryptedData,
              iv: infoRes.iv,
              token: wx.getStorageSync("loginFlag"),
            },
            success: requestRes => {
              if (requestRes.data.code === 200) {
                wx.setStorageSync("userInfo", requestRes.data.userInfo);
                wx.setStorageSync("loginFlag", requestRes.data.token);
                that.hideLoadingToast("登录成功", "success");
                wx.setStorageSync('hasLogin', true);
                if(that.isPerfect()){
                  wx.reLaunch({
                    url: '/pages/index',
                  });
                }
              } else {
                that.showInfo("登录失败");
              }
            },
            fail: () => {
              that.showInfo("调用服务端登录接口失败");
            }
          });
        } else {
          that.showInfo("获取 code 失败");
        }
      },
      fail: () => {
        that.showInfo("接口调用失败");
      }
    });
  },

  doBackLogin: function () {
    let that = this;
    let infoRes = wx.getStorageSync("infoRes");
    wx.login({
      success: loginRes => {
        if (loginRes.code){
          wx.request({
            url: api.loginUrl,
            method: "POST",
            data: {
              code: loginRes.code,
              rawData: infoRes.rawData,
              signature: infoRes.signature,
              encryptedData: infoRes.encryptedData,
              iv: infoRes.iv,
              token: wx.getStorageSync("loginFlag"),
            },
            success: requestRes => {
              if (requestRes.data.code === 200) {
                wx.setStorageSync("userInfo", requestRes.data.userInfo);
                wx.setStorageSync("loginFlag", requestRes.data.token);
                that.isPerfect();
                wx.setStorageSync('hasLogin', true);
              } else {
                that.showInfo("登录失败");
              }
            },
            fail: () => {
              that.hideLoadingToast("调用服务端登录接口失败");
            }
          });
        } else {
          that.hideLoadingToast("获取 code 失败");
        }
      },
      fail: () => {
        that.hideLoadingToast("接口调用失败");
      }
    });
  },

  hideLoadingToast: function (info = "error", icon = "none") {
    wx.hideLoading({
      success: () => {
        wx.showToast({
          title: info,
          icon: icon,
          duration: 2000,
        });
      },
    });
  },

  showInfo: function (info = "error", icon = "none") {
    wx.showToast({
      title: info,
      icon: icon,
      duration: 2000,
      mask: false,
    });
  },

  checkLoginStatus: function () {
    let that = this;
    let infoRes = wx.getStorageSync('infoRes');
    if (!infoRes) {
      wx.setStorageSync('hasLogin', false);
      setTimeout(function(){
        wx.redirectTo({
          url: '/pages/login/login',
        });
      },500);
    } else {
      let userInfo = wx.getStorageSync('userInfo');
      if (!userInfo) {
        wx.setStorageSync('hasLogin', false);
        that.doBackLogin();
      } else {
        let loginFlag = wx.getStorageSync('loginFlag');
        if (!loginFlag) {
          wx.setStorageSync('hasLogin', false);
          that.doBackLogin();
        } else {
          wx.checkSession({
            success: () => {
              wx.request({
                url: api.tokenUrl,
                  method: "POST",
                  data: {
                    token: loginFlag,
                  },
                success: res => {
                  if (res.data.code != 200) {
                    wx.setStorageSync('hasLogin', false);
                    that.doBackLogin();
                  }else{
                    wx.setStorageSync('hasLogin', true);
                  }
                },
                fail:() => {
                  wx.showToast({
                    title: "网络连接失败请检查",
                    icon: "none",
                    duration: 2000,
                  });
                }
              })
            },
            fail: () => {
              that.doBackLogin();
            }
          })
        }
      }
    }
  },


  isPerfect: function () {
    let userInfo = wx.getStorageSync("userInfo");
    if (userInfo.deptId == null) {
      wx.navigateTo({
        url: "/pages/setting/userDetail/userDetail",
      });
    } else {
      return true;
    }
  },

  onLaunch: function () {
    this.checkLoginStatus();
  },

});