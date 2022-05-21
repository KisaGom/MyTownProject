import { apiInstance } from "./index";

const api = apiInstance();

function houseDealList(params, success, fail) {
  api.get(`/house/deal/${params["dongCode"]}`).then(success).catch(fail);
}

export { houseDealList };
