import { apiInstance } from "./index";

const api = apiInstance();

function dealCountList(userid, success, fail) {
  api.get(`/dc/${userid}`).then(success).catch(fail);
}

export { dealCountList };
