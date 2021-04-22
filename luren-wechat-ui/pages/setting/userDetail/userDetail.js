const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Dialog from "@vant/weapp/dialog/dialog";
import Toast from '@vant/weapp/toast/toast';
Page({
  onShareAppMessage() {
    return {
      title: "详细信息",
      path: "/pages/setting/userDetail/userDetail",
    };
  },

  data: {
    Tree: {},
    userInfo: {},
    deptName: null,
    deptNameNew: null,
    deptId: null,
    isAgree: true,
    showPicker: false,
    columns: [],
    deptIndex: [0, 0, 0],
    errorList: {},
  },

  getUserInfo: function () {
    let userInfo = wx.getStorageSync("userInfo");
    this.setData({
      userInfo: userInfo,
    });
  },

  inputChange: function (e) {
    const { field } = e.currentTarget.dataset;
    this.setData({
      [`userInfo.${field}`]: e.detail,
    });
  },

  setLocation() {
    let that = this;
    wx.chooseLocation({
      success(res) {
        that.setData({
          [`userInfo.location`]: res.address,
        });
      },
      fail: () => {
        wx.getSetting({
          success: (res) => {
            if (!res.authSetting["scope.userLocation"]) {
              that.onClickConfirm();
            }
          },
        });
      },
    });
  },

  openSetting: function () {
    wx.openSetting();
  },

  onChange: function (e) {
    this.setData({
      isAgree: e.detail,
    });
  },

  onClickConfirm: function () {
    let message = "您拒绝了位置权限，是否去打开权限？";
    let that = this;
    Dialog.confirm({
      title: "位置信息",
      message,
    })
      .then(() => {
        that.openSetting();
      })
      .catch(() => {});
  },

  showPicker: function () {
    this.setData({
      showPicker: true,
    });
  },

  getTree: function () {
    let that = this;
    const _request = new request();
    _request.getRequest(api.deptTreeUrl).then((res) => {
      that.setData({
        Tree: res.data.data[0].children,
      });
      let Tree = res.data.data[0].children;
      let deptId = that.data.userInfo.deptId;
      let one = Tree;
      for (let i = 0; i < one.length; i++) {
        let two = one[i].children;
        for (let j = 0; j < two.length; j++) {
          let three = two[j].children;
          for (let x = 0; x < three.length; x++) {
            if (three[x].id == deptId) {
              that.setData({
                deptIndex: [i, j, x],
              });
              break;
            }
          }
        }
      }
      that.setData({
        columns: [
          {
            values: Tree,
            className: "column1",
            defaultIndex: that.data.deptIndex[0],
          },
          {
            values: Tree[0].children,
            className: "column2",
            defaultIndex: that.data.deptIndex[1],
          },
          {
            values: Tree[0].children[0].children,
            className: "column3",
            defaultIndex: that.data.deptIndex[2],
          },
        ],
      });
    });
  },

  onPickerConfirm: function () {
    this.setData({
      deptName: this.data.deptNameNew,
      [`userInfo.deptId`]: this.data.deptId,
    });
    this.onPickerCancel();
  },

  onPickerCancel:function () {
    this.setData({
      showPicker: false,
    });
  },

  onPickerChange: function (e) {
    const { picker, value } = e.detail;

    if (e.detail.index === 0) {
      picker.setColumnValues(1, value[0].children);
      picker.setColumnValues(2, value[0].children[0].children);
      this.setData({
        deptNameNew:
          value[0].label +
          "-" +
          value[0].children[0].label +
          "-" +
          value[0].children[0].children[0].label,
        deptId: value[0].children[0].children[0].id,
      });
    } else if (e.detail.index === 1) {
      picker.setColumnValues(2, value[1].children);
      this.setData({
        deptNameNew:
          value[0].label +
          "-" +
          value[1].label +
          "-" +
          value[1].children[0].label,
        deptId: value[1].children[0].id,
      });
    } else if (e.detail.index == 2) {
      picker.setColumnValues(2, value[1].children);
      this.setData({
        deptNameNew:
          value[0].label + "-" + value[1].label + "-" + value[2].label,
        deptId: value[2].id,
      });
    }
  },

  doValid: function () {
    let that = this;
    let userInfo = that.data.userInfo;
    let re = /^1[3-9]\d{9}$/;
    that.setData({ errorList: null });
    if (!userInfo.name || userInfo.name == "") {
      that.setData({
        [`errorList.name`]: "姓名不能为空",
      });
      return false;
    } else {
      if (!userInfo.number || userInfo.number == "") {
        that.setData({
          [`errorList.number`]: "姓名不能为空",
        });
        return false;
      } else {
        if (!userInfo.mobile || userInfo.mobile == "") {
          that.setData({
            [`errorList.mobile`]: "手机号不能为空",
          });
          return false;
        } else if (userInfo.mobile.length < 11) {
          that.setData({
            [`errorList.mobile`]: "手机号位数不够",
          });
          return false;
        } else if (!re.test(userInfo.mobile)) {
          that.setData({
            [`errorList.mobile`]: "手机号格式不正确",
          });
          return false;
        } else {
          if (!userInfo.location || userInfo.location == "") {
            that.setData({
              [`errorList.location`]: "居住地不能为空",
            });
            return false;
          } else {
            if (!userInfo.emergencyContact || userInfo.emergencyContact == "") {
              that.setData({
                [`errorList.emergencyContact`]: "紧急联系人不能为空",
              });
              return false;
            } else {
              if (
                !userInfo.emergencyContactMobile ||
                userInfo.emergencyContactMobile == ""
              ) {
                that.setData({
                  [`errorList.emergencyContactMobile`]: "紧急联系人号码不能为空",
                });
                return false;
              } else if (userInfo.emergencyContactMobile.length < 11) {
                that.setData({
                  [`errorList.emergencyContactMobile`]: "紧急联系人手机号位数不够",
                });
                return false;
              } else if (!re.test(userInfo.emergencyContactMobile)) {
                that.setData({
                  [`errorList.emergencyContactMobile`]: "紧急联系人手机号格式不正确",
                });
                return false;
              } else {
                return true;
              }
            }
          }
        }
      }
    }
  },

  submitForm:function () {
    let that = this;
    if (that.doValid()) {
      if (that.data.isAgree) {
        Toast.loading({
          message: "修改中",
          duration: 5000,
        })
        setTimeout(function () {
          that.submit();
          let pages = getCurrentPages();
          let prevpage = pages[pages.length - 2];
          if (prevpage.route == "pages/login/login") {
            wx.navigateBack({
              delta: 2,
            });
          } else {
            wx.navigateBack({
              delta: 1,
            });
          }
        }, 1500);
      } else {
        Toast("请同意相关条款");
      }
    }
  },

  submit: function () {
    let that = this;
    let userInfo = that.data.userInfo;
    const _request = new request();
    _request
      .postRequest(api.updateUrl, {
        userInfo: userInfo,
      })
      .then((res) => {
        console.log(res)
        if (res.data.code === 200) {
          Toast.clear();
          Toast.success("修改成功");
          let userInfo = that.data.userInfo;
          wx.setStorageSync("userInfo", userInfo);
        } else {
          Toast.clear();
          Toast.fail("修改失败");
        }
      }).catch(()=>{
        Toast.clear();
        Toast.fail("服务器错误");
      });
  },

  onLoad: function () {
    let that = this;
    that.getUserInfo();
    that.getTree();
    setTimeout(function () {
      let deptIndex = that.data.deptIndex;
      let Tree = that.data.Tree;
      let deptId = Tree[deptIndex[0]].children[deptIndex[1]].children[deptIndex[2]].id;
      that.setData({
        deptName:
          Tree[deptIndex[0]].label +
          "-" +
          Tree[deptIndex[0]].children[deptIndex[1]].label +
          "-" +
          Tree[deptIndex[0]].children[deptIndex[1]].children[deptIndex[2]]
            .label,
        deptId: deptId,
        [`userInfo.deptId`]: deptId,
      });
    }, 500);
  },

  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },
});
