import { apiInstance } from "./index";

const api = apiInstance();

function averageAgeInfo(params, success, fail) {
  api.get(`/info/age/${params["dongCode"]}`).then(success).catch(fail);
}

function incomeInfo(params, success, fail) {
  api.get(`/info/income/${params["gugunName"]}`).then(success).catch(fail);
}

function schoolInfo(params, success, fail) {
  api.get(`/info/income/${params["gugunName"]}`).then(success).catch(fail);
}

export { averageAgeInfo, incomeInfo, schoolInfo };
