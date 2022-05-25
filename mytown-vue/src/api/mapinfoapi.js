import { apiInstance } from "./index";

const api = apiInstance();

async function avgDealAmount(dongCode, success, fail) {
  await api.get(`/mapi/ada/${dongCode}`).then(success).catch(fail);
}

async function dealCount(dongCode, success, fail) {
  await api.get(`/mapi/dc/${dongCode}`).then(success).catch(fail);
}

async function avgMRentAmount(dongCode, success, fail) {
  await api.get(`/mapi/amra/${dongCode}`).then(success).catch(fail);
}

async function avgTRentAmount(dongCode, success, fail) {
  await api.get(`/mapi/atra/${dongCode}`).then(success).catch(fail);
}

async function rentCount(dongCode, success, fail) {
  await api.get(`/mapi/rc/${dongCode}`).then(success).catch(fail);
}

async function avgAge(dongCode, success, fail) {
  await api.get(`/mapi/aa/${dongCode}`).then(success).catch(fail);
}

export {
  avgDealAmount,
  dealCount,
  avgMRentAmount,
  avgTRentAmount,
  rentCount,
  avgAge,
};
