import { apiInstance } from "./index";

const api = apiInstance();

function commercialListDong(dongCode, success, fail) {
  api.get(`/com/dong/${dongCode}`).then(success).catch(fail);
}

export { commercialListDong };
