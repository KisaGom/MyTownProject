import { apiInstance } from "./index";

const api = apiInstance();

function commercialListDong(params, success, fail) {
  api.get(`/com/dong/${params["dongCode"]}`).then(success).catch(fail);
}

export { commercialListDong };
