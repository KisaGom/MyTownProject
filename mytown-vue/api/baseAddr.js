import { apiInstance } from "./index";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/addr/sido`).then(success, fail);
}

function gugunList(params, success, fail) {
  api.get(`/addr/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/addr/dong`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList };
