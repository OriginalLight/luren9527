//区分开发环境和生产环境
const current = "dev";


const profiles = {
  //服务器开发环境地址
  dev: {
    baseURL: "https://localhost:443/",
    baseApi: "api/",
  },
  //服务器生产环境地址
  prod: {
    baseURL: "https://www.devliuhh.xyz/",
    baseApi: "api/",
  },
};

// 服务器域名
const baseUrl = profiles[current].baseURL;
//api路径
const baseApi = profiles[current].baseApi;

// 登录接口
const loginUrl = baseUrl + baseApi + "login";
const logoutUrl = baseUrl + baseApi + "logout";
const deptTreeUrl = baseUrl + "system/dept/tree";
const tokenUrl = baseUrl + baseApi + "token";
const updateUrl = baseUrl + baseApi + "update";
const getTemperatureUrl = baseUrl + "wx/temperature/findAllByOpenId";
const addTemperatureUrl = baseUrl + "wx/temperature/add";
const toDayTemperatureUrl = baseUrl + "wx/temperature/toDay";
const addTripUrl = baseUrl + "wx/trip/add";
const getTripUrl = baseUrl + "wx/trip/findAllByOpenId";
const addLeaveUrl = baseUrl + "wx/leave/add";
const getLeaveUrl = baseUrl + "wx/leave/findAllByOpenId";
const reSetLeaveUrl = baseUrl + "wx/leave/reSet";
const addBackUrl = baseUrl + "wx/back/add";
const getBackUrl = baseUrl + "wx/back/findAllByOpenId";
const reSetBackUrl = baseUrl + "wx/back/reSet";
const getNoticeUrl =  baseUrl + "wx/notice/findAll";

/*
Tencent  Face-2-Face Translator.
*/

module.exports = {
  baseUrl: baseUrl,
  baseApi: baseApi,
  loginUrl: loginUrl,
  logoutUrl: logoutUrl,
  tokenUrl: tokenUrl,
  deptTreeUrl: deptTreeUrl,
  updateUrl: updateUrl,
  addTemperatureUrl: addTemperatureUrl,
  getTemperatureUrl: getTemperatureUrl,
  toDayTemperatureUrl: toDayTemperatureUrl,
  addTripUrl: addTripUrl,
  getTripUrl: getTripUrl,
  addLeaveUrl: addLeaveUrl,
  getLeaveUrl: getLeaveUrl,
  addBackUrl: addBackUrl,
  getBackUrl: getBackUrl,
  reSetBackUrl: reSetBackUrl,
  reSetLeaveUrl: reSetLeaveUrl,
  getNoticeUrl: getNoticeUrl,
};
