const app = getApp();
import Toast from "@vant/weapp/toast/toast";

Page({
  onShareAppMessage() {
    return {
      title: "登录",
      path: "/pages/login/login",
    };
  },
  data: {
    canIUseOpenData:
      wx.canIUse("open-data.type.userAvatarUrl") &&
      wx.canIUse("open-data.type.userNickName"),
  },

  // 公共登录动作
  doLogin: function () {
    wx.getUserProfile({
      desc: "获取你的昵称、头像、地区及性别",
      success: (infoRes) => {
        wx.setStorageSync("infoRes", infoRes);
        app.doLogin();
      },
      fail: () => {
        Toast.fail("授权失败");
      },
    });
  },
});
