
Page({
  
  onShareAppMessage: function () {
    return {
      title: "相关条款",
      path: "/pages/public/agreement/agreement",
    };
  },
  data: {},

  
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },
  
});
