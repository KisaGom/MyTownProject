import { apiInstance } from "./index";

const api = apiInstance();

function getAddrByCode(params, success, fail) {
  api.get(`/addr/base/${params}`).then(success).catch(fail);
}

function sidoList(success, fail) {
  api.get(`/addr/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/addr/gugun/${params["sido"]}`).then(success).catch(fail);
}

function dongList(params, success, fail) {
  // console.log(params);
  api.get(`/addr/dong/${params["gugun"]}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, getAddrByCode };
