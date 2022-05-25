import { apiInstance } from "./index";

const api = apiInstance();

function avgDealAmountList(userid, success, fail) {
  api.get(`/stat/ada/${userid}`).then(success).catch(fail);
}

function dealCountList(userid, success, fail) {
  api.get(`/stat/dc/${userid}`).then(success).catch(fail);
}

function incomeList(userid, success, fail) {
  api.get(`/stat/tc/${userid}`).then(success).catch(fail);
}

function avgTRentAmount(userid, success, fail) {
  api.get(`/stat/ara/total/${userid}`).then(success).catch(fail);
}

function avgMRentAmount(userid, success, fail) {
  api.get(`/stat/ara/monthly/${userid}`).then(success).catch(fail);
}

function rentCountList(userid, success, fail) {
  api.get(`/stat/rc/${userid}`).then(success).catch(fail);
}

function ageAverageList(userid, success, fail) {
  api.get(`/stat/ag/${userid}`).then(success).catch(fail);
}

export {
  dealCountList,
  incomeList,
  rentCountList,
  ageAverageList,
  avgDealAmountList,
  avgTRentAmount,
  avgMRentAmount,
};
