import { apiInstance } from "./index";

const api = apiInstance();

function houseDealList(dongCode, success, fail) {
  api.get(`/house/deal/${dongCode}`).then(success).catch(fail);
}

export { houseDealList };
